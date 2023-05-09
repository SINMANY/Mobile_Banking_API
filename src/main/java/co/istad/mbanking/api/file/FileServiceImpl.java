package co.istad.mbanking.api.file;

import co.istad.mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileServiceImpl implements FileService{
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.base-url-download}")
    private String fileBaseUrlDownload;
    @Autowired
    private void setFileUtil(FileUtil fileUtil){
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
       return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();
        for(MultipartFile file : files){
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

    @Override
    public List<FileDto> findAllFiles() {
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        List<FileDto> fileDtoList = new ArrayList<>();
        assert files != null;
        for(File folderFile: files){
            fileDtoList
                    .add(
                            new FileDto(
                                    folderFile.getName()
                                    ,fileBaseUrl + folderFile.getName()
                                    ,folderFile.getName().substring(folderFile.getName().lastIndexOf(".") + 1)
                                    ,fileBaseUrlDownload + folderFile.getName().substring(0,folderFile.getName().length()-4)
                                    ,folderFile.length()
                            )
                    );
        }
        return fileDtoList;
    }


    @Override
    public FileDto findFileByName(String fileName){
        return fileUtil.findFileByName(fileName);
    }
    @Override
    public void removeAllFiles() {
        fileUtil.removeAllFiles();
    }
    @Override
    public String removeFileByName(String fileName) {
        return fileUtil.removeFileByName(fileName);
    }
    @Override
    public String downloadFile(String filename) {
        File file = new File(fileServerPath);
        File [] files = file.listFiles();
        Path path = null;
        String resource;
        assert files != null;
        for(File file1 : files){
            String name = fileUtil.name(filename,file1);
            if(name.equals(filename)){
                path  = Paths.get(fileServerPath + file1.getName()).toAbsolutePath().normalize();
            }
        }
        assert path != null;
        resource = new String(String.valueOf(path.toUri()));
        return resource;
    }
}
