package ch.hearc.cuddle.helpers;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHelper {

    public static final String[] ALLOWED_FILES = {"image/png", "image/jpeg", "image/jpg", "image/gif", "image/webp"};

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("create file:");
            System.out.println(filePath);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void deleteFile(String uploadDir, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        // FIXME doesnt delete
        Path filePath = uploadPath.resolve(fileName);

        System.out.println("delete file:");
        System.out.println(uploadDir);
        System.out.println(uploadPath);
        System.out.println(fileName);
        System.out.println(filePath);
        System.out.println(Files.exists(filePath));

        if(Files.exists(filePath))
            Files.delete(filePath);
    }
}
