package com.server.home;

import java.time.LocalDateTime;

public class FileMetadata {
    private Long id;
    private String originalFilename;
    private String uniqueFilename;
    private long size;
    private LocalDateTime uploadTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getUniqueFilename() {
        return uniqueFilename;
    }

    public void setUniqueFilename(String uniqueFilename) {
        this.uniqueFilename = uniqueFilename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
