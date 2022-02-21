package by.zhigalko.snow.world.util;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.extern.log4j.Log4j2;
import javax.naming.*;
import javax.sql.DataSource;

@Log4j2
public class ImageUploader {
    private static volatile ImageUploader instance = null;

    private ImageUploader() {}

    public static ImageUploader getInstance() {
        if (instance == null) {
            synchronized (ImageUploader.class) {
                if (instance == null) {
                    instance = new ImageUploader();
                }
            }
        }
        return instance;
    }
    private MinioClient getMinioClient() {
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("minio");
            //todo

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return MinioClient.builder()
                //todo add endpoint and credentials
                .build();
    }
    public boolean uploadImage(String bucketName, String objectName, String fileName) {
        MinioClient minioClient = getMinioClient();
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if(bucketExists) {
                minioClient.uploadObject(
                        UploadObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .filename(fileName)
//                                .bucket("img")
//                                .object("test.png")
//                                .filename("/home/evgeniy/Pictures/img-project/test.png")
                                .build());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
