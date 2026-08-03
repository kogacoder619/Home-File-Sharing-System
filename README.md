# Home File Sharing System
Mentorship Project

    # Overview
      Building a personal file sharing system for home network use. users will be able to upload, browse, download, and delete 
      files through the system rather than accessing a shared drive. v1 will be small - only a singular user and later versions will expand.

    # Users 
      v1 will consist of only a single user

    # Functional Requirements
      FR 1 = a user can upload a file to the system
      FR 2 = a user can view a list of files currently stored in the system (file name, size, upload date)
      FR 3 = a user can download a previously uploaded file
      FR 4 = a user can delete a file from the system
      FR 5 = The system must reject uploads over a file size max threshold and return error when this occurs
      FR 6 = the system will reject uploads of disallowed file types ( define and document allow-list/deny-list)
      FR 7 = if user uploads a file with the same name as another existing file it must handle it in a non-destructive
             way i.e. reject/rename/version# while also documenting the behavior
      Fr 8 = the system must expose its functionality via a documented APT(REST). Frontend/UI is out of scope for v1 
             API access (e.g., via curl/Postman) is sufficient
      FR 9 = the system must persist file metadata ( name, size, upload date, storage location) in a database, not just
             rely on the filesystem alone


    # Non-Functional Requirements
      NFR 1 = system must prevent path traversal attacks - crafted filename must not allow reading/writing outside the intended storage directory.
      NFR 2 = uploaded files must never be stored or served in a way that allows them to be executed by the server
      NFR 3 = all user supplied input must be validated before use - do not trust the client input
      NFR 4 = a failed upload must not leave a corrupted or partial file visible in the list
      NFR 5 = the system should support files up to atleast 500 mb without running out of memory
      NFR 6 = code must be organized in layers (controller / service / repository or equiv) rather than putting logic directly in controllers
      NFR 7 = core logic ( file validation, storage handling) must have automated unit tests
      NFR 8 = the system must log key events at an appropriate log level
      NFR 9 = key limits (max file size, storage location, allowed file types) must be externally configurable(via application.properties), not hardcoded

    # Assumptions and Constraints
      - backend only for v1 - java & springboot. no frontend required; API-level testing(Postman/curl) is acceptable proof of functionality
      - no authentication/authorization required for v1, LAN-only scope - but this note explicitly as a known limitation, not an oversight
      - local disk storage for v1. Cloud storage is a future consideration, not required yet.
      - no requirement to handle concurrent uploads from multiple simultaneous users in v1.
    
    # Out of scope for v1
      - multiuser accounts, authentication, or permissions
      - file sharing between users
      - web based ui ( react or otherwise)
      - cloud storage integration
      - search, tagging, or folder/directory organization
      - virus/malware scanning of uploaded files

    # Acceptance Criteria (v1)
      1. All functional requirements FR1-FR9 implemented and demonstratable via API calls
      2. All non-functional requirements NFR1-NFR9 addressed and can be explained/demonstrated not just assumed
      3. unit tests exist and pass for core logic
      4. a short README explains how to run the system locally and how to exercise each endpoint
      5. a brief demo/walkthrough for the product owner covering: a normal upload/download/delete flow, and atleast one deliberate attempt to break it, showing how its handled correctly