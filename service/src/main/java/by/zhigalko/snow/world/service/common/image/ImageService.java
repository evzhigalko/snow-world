package by.zhigalko.snow.world.service.common.image;

import by.zhigalko.snow.world.dao.image.ImageDaoImpl;
import by.zhigalko.snow.world.entity.Image;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ImageService {
    private final ImageDaoImpl imageDao;
    private final ImageUploader imageUploader;

    @Autowired
    public ImageService(ImageDaoImpl imageDao, ImageUploader imageUploader) {
        this.imageDao = imageDao;
        this.imageUploader = imageUploader;
    }

    public Image getImage(String imageName) {
        Image image = new Image();
        image.setImageName(imageName);
        return image;
    }

    public void save(Image image) {
        imageDao.save(image);
    }

    public String uploadImage(Part partFile, String bucketName, String imageName) throws IOException {
       return imageUploader.uploadImage(partFile, bucketName, imageName);
    }
}
