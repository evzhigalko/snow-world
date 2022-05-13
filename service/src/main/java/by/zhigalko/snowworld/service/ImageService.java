package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.entity.Image;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Service for operations with Image
 */
public interface ImageService {
    /**
     * Get image by
     * @param imageName is received from presentation layer, add from admin page
     * @return {@link Image}
     */
    Image getImage(String imageName);

    /**
     * Save image into database
     * @param image {@link Image}
     * @return saved {@link Image}
     */
    Image save(Image image);

    /**
     * Delete image from database
     * @param image {@link Image}
     */
    void delete(Image image);

    /**
     * Upload image from presentation layer to service layer.
     * @param partFile {@link MultipartFile} image uploaded from presentation layer
     * @param bucketName name of bucket in MinIO object storage.
     * @return saved image name
     * @throws IOException
     */
    String uploadImage(MultipartFile partFile, String bucketName) throws IOException;
}
