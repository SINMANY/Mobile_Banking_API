package co.istad.mbanking.api.file;

import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    @Value("${file.base-url-download}")
    private String fileBaseDownloadUrl;
    private String downloadLink;
    @PostMapping
    public BaseRest<?> uploadSingleFile(@RequestPart MultipartFile file){
        FileDto fileDto = fileService.uploadSingle(file);
        return  BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("File have been uploaded!")
                .data(fileDto)
                .build();
    }

    @PostMapping("/uploadMultipleFiles")
    public BaseRest<?> uploadMultipleFiles(@RequestPart List<MultipartFile> files){
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return  BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("File have been uploaded!")
                .data(fileDto)
                .build();
    }
    @GetMapping("/findAllFiles")
    public BaseRest<?> findAllFiles() {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.findAllFiles())
                .message("Files have found successfully.")
                .build();
    }
    @GetMapping("/{fileName}")
    public BaseRest<?> findFileByName(@PathVariable("fileName") String fileName) {
        FileDto fileDto = fileService.findFileByName(fileName);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .message("Files found successfully.")
                .build();
    }
    @DeleteMapping("/deleteAllFiles")
    public BaseRest<?> removeAllFiles() {
        fileService.removeAllFiles();
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data("All files have been removed.")
                .message("Files have been removed successfully.")
                .build();
    }
    @DeleteMapping("/removeFileByName/{fileName}")
    public BaseRest<?> removeFile(@PathVariable String fileName) {
        String filename = fileService.removeFileByName(fileName);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(null)
                .message("File has been removed successfully.")
                .build();
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile_(@PathVariable("filename") String filename){
        String resource = fileService.downloadFile(filename);
        this.downloadLink = "Download link: " + fileBaseDownloadUrl + filename;
        System.out.println(downloadLink);
        try {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +resource.getBytes()+ "\"")
                    .body(resource);
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File is not found.");
        }
    }
}
