package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.repository.ImageRepository;
import by.zhigalko.snow.world.service.ImageService;
import by.zhigalko.snow.world.service.util.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageUploader imageUploader;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageUploader imageUploader) {
        this.imageRepository = imageRepository;
        this.imageUploader = imageUploader;
    }

    @Override
    public Image getImage(String imageName) {
        Image image = new Image();
        image.setImageName(imageName);
        return image;
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public String uploadImage(MultipartFile partFile, String bucketName, String imageName) throws IOException {
       return imageUploader.uploadImage(partFile, bucketName, imageName);
    }
}
