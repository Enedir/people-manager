package sajadvpm.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sajadvpm.exception.FileStorageException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.IFileService;
import sajadvpm.rest.payload.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/api/uploads")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileStorageService fileStorageService;
    private IFileService fileService;

    public FileController(FileStorageService fileStorageService, IFileService fileService) {
        this.fileStorageService = fileStorageService;
        this.fileService = fileService;
    }

    @PostMapping("/images")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            var fileName = fileStorageService.storeFile(file);
            var avatarId = fileService.add(file.getName(), fileName);

            return new ResponseEntity(avatarId, new HttpHeaders(), HttpStatus.OK);
        } catch (FileStorageException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
