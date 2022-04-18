package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.Image;

/**
 * DAO for Image
 */
public interface ImageDao {
    /**
     * Save entity.
     * @param image saving image
     */
    Image saveImage(Image image);
    /**
     * Delete entity.
     * @param image deleting image
     */
    void deleteImage(Image image);
}
