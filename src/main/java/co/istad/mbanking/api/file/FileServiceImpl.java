package co.istad.mbanking.api.file;

import co.istad.mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileServiceImpl implements FileService{
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
    @Autowired
    private void setFileUtil(FileUtil fileUtil){

        this.fileUtil = fileUtil;
    }

//    Upload single file
    @Override
    public FileDto uploadSingle(MultipartFile file) {

        return fileUtil.upload(file);
    }

//    Upload multiple files
    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();
        for(MultipartFile file : files){
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

//  Find all files
    @Override
    public List<FileDto> findAllFiles() {
        List<FileDto> fileDtoList = new ArrayList<>();
        if(fileDtoList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "There are no files to search..!");
        File folder = new File(this.fileServerPath);
        File[] files = folder.listFiles();
        for (File file : files){
            if (file.isFile()){
                String name = file.getName();
                String url = this.fileBaseUrl+ "/files/" + name;
                int lastIndex = name.lastIndexOf('.');
                String ext = name.substring(lastIndex+1);
                long size = file.length()/1024;
                String download = fileBaseUrl + "/api/v1/files/download/" + name;
                fileDtoList.add(new FileDto(name, url, ext,download,size));
            }
        }
        return fileDtoList;
    }

    // Find file by name
    @Override
    public FileDto findFileByName(String name) throws IOException {
        Resource resource = fileUtil.findFileByName(name);
        return FileDto.builder()
                .name(resource.getFilename())
                .extension(fileUtil.getExtension(resource.getFilename()))
                .url(String.format("%s%s",
                        fileUtil.getFileBaseUrl(),
                        resource.getFilename()))
                .downloadUrl(String.format("%s%s", fileDownloadUrl, name))
                .size(resource.contentLength())
                .build();
    }

//    Delete file by name

    @Override
    public void deleteFileByName(String name) throws IOException {
        fileUtil.deleteByName(name);
    }

    @Override
    public boolean deleteAllFiles() {
        if(this.findAllFiles().isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Files are not found!");
        this.findAllFiles().forEach(fileDto -> {
            try {
                this.deleteFileByName(fileDto.name());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Failed to delete!");
            }
        });
        return true;
    }

    // download file
    @Override
    public Resource download(String name) {
        return fileUtil.findFileByName(name);
    }
}
