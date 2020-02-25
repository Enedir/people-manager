package sajadvpm.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sajadvpm.exception.FileStorageException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.IFileService;
import sajadvpm.rest.payload.FileStorageService;


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


}
