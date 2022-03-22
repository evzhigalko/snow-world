package by.zhigalko.snow.world.service.common.image;

import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageUploader imageUploader;

    @Autowired
    public ImageService(ImageRepository imageRepository, ImageUploader imageUploader) {
        this.imageRepository = imageRepository;
        this.imageUploader = imageUploader;
    }

    public Image getImage(String imageName) {
        Image image = new Image();
        image.setImageName(imageName);
        return image;
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void delete(Image image) {
        imageRepository.delete(image);
    }

    public String uploadImage(MultipartFile partFile, String bucketName, String imageName) throws IOException {
       return imageUploader.uploadImage(partFile, bucketName, imageName);
    }
}
