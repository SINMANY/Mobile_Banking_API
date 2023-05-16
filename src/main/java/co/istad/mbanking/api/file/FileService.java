package co.istad.mbanking.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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

    List<FileDto> findAllFiles();

    /**
     * uses to find file by name
     * @param name of file
     * @return fileDto
     * @throws IOException internal error
     */
    FileDto findFileByName(String name) throws IOException;

    /**
     * Uses to delete file by name
     * @param name of file
     * @throws IOException internal error
     */
    void deleteFileByName(String name) throws IOException;

    boolean deleteAllFiles();

    /**
     * Uses to download file by name
     * @param name of file
     * @return file
     */
    Resource download(String name);
}
