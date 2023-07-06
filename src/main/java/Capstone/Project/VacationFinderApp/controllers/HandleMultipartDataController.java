package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.DatabaseFile;
import Capstone.Project.VacationFinderApp.models.FileResponse;
import Capstone.Project.VacationFinderApp.repositories.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RestController
public class HandleMultipartDataController {

    @Autowired
    DatabaseFileRepository fileRepository;

    @PostMapping("/uploadSingleFile")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {

        String fileName;
        // get the original file name
        if (file == null) {
            return ResponseEntity.badRequest().body(
                    new IllegalStateException("Sorry did not receive a file, please try again!"));
        } else {
            fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        }

        try {
            // create a new DatabaseFile with the file information
            final DatabaseFile databaseFile = DatabaseFile.builder()
                    .data(file.getBytes())
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .build();

            // save to the database
            final DatabaseFile savedFile = fileRepository.save(databaseFile);

            // create the download URI
            savedFile.setDownloadUrl(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(savedFile.getId()))
                    .toUriString());

            // create a FileResponse object using file info and wrap it in a ResponseEntity
            return ResponseEntity.ok(FileResponse.builder()
                    .fileName(databaseFile.getFileName())
                    .fileDownloadUri(savedFile.getDownloadUrl())
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .build());

        } catch (IOException ex) {
            // wraps exception with custom message in a ResponseEntity to be returned to the user.
            return ResponseEntity.badRequest().body(
                    new IllegalStateException("Sorry could not store file " + fileName + "Try again!", ex));
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFileById(@PathVariable(name = "id") Long fileId) {

        final Optional<DatabaseFile> optional = fileRepository.findById(fileId);

        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found with id: " + fileId);
        }

        DatabaseFile databaseFile = optional.get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                // display the file inline
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                // download file, without setting file name
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                // download file, and specify file name
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", databaseFile.getFileName()))
                .body(new ByteArrayResource(databaseFile.getData()));
    }
}
