package com.server.home;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private static final int BUFFER_SIZE = 8192; // 8KB buffer size

    @Value("${storage.location}")
    private String storageLocation;

    @Value("${storage.maxFileSize}")
    private long maxFileSize;

    @Value("${storage.allowedFileTypes}")
    private String[] allowedFileTypes;

    @Value("${storage.allowedFileExtensions}") 
    private String[] allowedFileExtensions;

    private final FileRepository fileRepository;
    private Path storagePath;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostConstruct
    public void init() {
        storagePath = Paths.get(storageLocation);
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            logger.error("Could not create storage directory: {}", e.getMessage());
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    FileMetadata uploadFile(MultipartFile file) throws IOException {
        validateFile(file);
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;
        Path targetLocation = storagePath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        FileMetadata metadata = new FileMetadata();
        metadata.setOriginalFilename(originalFilename);
        metadata.setUniqueFilename(uniqueFilename);
        metadata.setSize(file.getSize());
        metadata.setUploadTime(LocalDateTime.now());
        return fileRepository.save(metadata);
    }

    void deleteFile(Long id) {
        FileMetadata metadata = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
        Path filePath = storagePath.resolve(metadata.getUniqueFilename());
        try {
            Files.deleteIfExists(filePath);
            fileRepository.delete(metadata);
        } catch (IOException e) {
            logger.error("Could not delete file: {}", e.getMessage());
            throw new RuntimeException("Could not delete file", e);
        }

    private FileMetadata findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StoredFileNotFoundException("No file found with id " + id));
    }
}
