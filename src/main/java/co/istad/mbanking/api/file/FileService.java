package co.istad.mbanking.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     * uses to upload a single file
     * @param file request form data from client
     * @return FileDto
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * uses to upload multiple files
     * @param fileList request form data from client
     * @return FileList
     */

    List<FileDto> uploadMultiple(List<MultipartFile> fileList);
    FileDto findFileByName(String fileName);

    List<FileDto> findAllFiles();

    void removeAllFiles();

    String removeFileByName(String fileName);

    String downloadFile(String fileName);
}
