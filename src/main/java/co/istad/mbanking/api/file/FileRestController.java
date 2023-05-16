package co.istad.mbanking.api.file;

import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
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

    @GetMapping
    public BaseRest<?> get(){
        List<FileDto> fileDtoList = fileService.findAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been found!")
                .timestamp(LocalDateTime.now())
                .data(fileDtoList)
                .build();
    }

    @GetMapping("/{name}")
    public BaseRest<?> findFileByName(@PathVariable String name) throws IOException {
        FileDto fileDto = fileService.findFileByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been found!")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @DeleteMapping
    public BaseRest<?> deleteAllFiles(){
        boolean deleted = fileService.deleteAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Files has been deleted!")
                .timestamp(LocalDateTime.now())
                .data(deleted)
                .build();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) throws IOException {
       fileService.deleteFileByName(name);
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<?> download(@PathVariable String name){
        Resource resource = fileService.download(name);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

