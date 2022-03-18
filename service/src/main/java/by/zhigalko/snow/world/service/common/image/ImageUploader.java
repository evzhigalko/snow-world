package by.zhigalko.snow.world.service.common.image;

import io.minio.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

@Log4j2
@Service
public class ImageUploader {
    private final MinioClient minioClient;
    private final Environment env;
    private final String minioServiceUrl;

    @Autowired
    public ImageUploader(MinioClient minioClient, Environment env) {
        this.minioClient = minioClient;
        this.env = env;
        minioServiceUrl = env.getProperty("minio.url");
    }

    public String uploadImage(MultipartFile partFile, String bucketName, String imageName) throws IOException {
        String objectName = "";
        if (bucketName.contains("snowboard")) {
            bucketName = "snowboard";
        } else if (bucketName.contains("ski")) {
            bucketName = "ski";
        } else {
            bucketName = "clothes";
        }
        if (imageName != null) {
            String contentType = partFile.getContentType();
            InputStream inputStream = partFile.getInputStream();
            Long length = (long) inputStream.available();
            objectName = minioUpload(bucketName, imageName, inputStream, length, contentType);
            log.info(">>> Image is uploaded: " + objectName);
        }
        return minioServiceUrl + bucketName + "/" + objectName;
    }

    private String minioUpload(String bucketName, String objectName, InputStream inputStream, Long length, String contentType) {
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
            return "";
        }
        return objectName;
    }
}
