package co.istad.mbanking.util;

import co.istad.mbanking.api.file.FileDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.client-path}")
    private String fileBaseUrl;
    @Value("${file.base-url-download}")
    private String fileBaseDownloadUrl;
    public FileDto upload(MultipartFile file){
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex + 1);
        long size = file.getSize();
        String name = String.format("%s.%s", UUID.randomUUID(),extension);
        String url = String.format("%s%s", fileBaseUrl, name);

        Path path = Paths.get(fileServerPath + name);
        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(name)
                    .url(url)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload file failed! Please contact Developer [0886610148].");
        }
    }
    public FileDto findFileByName(String fileName){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        assert files != null;
        for(File folderfile: files){
            String name = this.name(fileName, folderfile);
            if(name.equals(fileName)){
                return FileDto
                        .builder()
                        .name(folderfile.getName())
                        .url(fileBaseUrl + folderfile.getName())
                        .downloadUrl(fileBaseDownloadUrl + fileName)
                        .extension(folderfile.getName().substring(folderfile.getName().lastIndexOf(".") + 1))
                        .size(folderfile.length())
                        .build();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File is not found.");
    }
    public String removeFileByName(String fileName){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        assert files != null;
        for(File file1: files){
            String name = this.name(fileName, file1);
            System.out.println(name);
            if(name.equals(fileName)){
                file1.delete();
                return "File has been remove success.";
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File is not found.");
    }
    public void removeAllFiles(){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        try{
            assert files != null;
            for(File file1: files){
                file1.delete();
            }
        }catch (Exception exception){
            return;
        }
    }
    @NotNull
    public String name(String name, File file1){
        if(file1.getName().length()==40){
            name = file1
                    .getName()
                    .substring(0,file1.getName().length()-4);
        }else {
            name = file1
                    .getName()
                    .substring(0,file1.getName().length()-5);
        }
        System.out.println(name);
        return name;

}}
