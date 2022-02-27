package by.zhigalko.snow.world.util;

import io.minio.*;
import jakarta.servlet.http.Part;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Log4j2
@Service
public class ImageUploader {
    private final MinioClient minioClient;

    @Autowired
    public ImageUploader(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public boolean uploadImage(Part partFile, String bucketName, String imageName) throws IOException {
        String filename = partFile.getSubmittedFileName();
        boolean isUploaded = false;
        if (filename!=null) {
            String contentType = partFile.getContentType();
            InputStream inputStream = partFile.getInputStream();
            Long length = (long) inputStream.available();
            isUploaded= minioUpload(bucketName, imageName, inputStream, length, contentType);
            log.info("Image is uploaded >>> " + isUploaded);
        }
        return isUploaded;
    }

    private boolean minioUpload(String bucketName, String objectName, InputStream inputStream, Long length, String contentType) {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName).stream(
                                    inputStream, length, -1)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            log.info(">>> Error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
