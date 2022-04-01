package by.zhigalko.snow.world.service.common.image;

import by.zhigalko.snow.world.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public interface ImageService {
    Image getImage(String imageName);
    void save(Image image);
    void delete(Image image);
    String uploadImage(MultipartFile partFile, String bucketName, String imageName) throws IOException;
}
