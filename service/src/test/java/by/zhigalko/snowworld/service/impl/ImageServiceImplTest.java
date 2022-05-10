package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.BucketName;
import by.zhigalko.snowworld.repository.ImageRepository;
import by.zhigalko.snowworld.service.util.ImageUploader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
    private Image image;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageUploader imageUploader;

    @BeforeEach
    void setUp() {
        image = new Image();
        image.setImageName("asd");
        image.setId(UUID.randomUUID());
    }

    @Test
    void getItemIfImageNameNotNullTest() {
        Image image = imageService.getImage("dummy");

        assertThat(image).isNotNull();
        assertThat(image.getImageName()).isEqualTo("dummy");
    }

    @Test
    void getItemIfImageNameIsNullTest() {
        Image image = imageService.getImage(null);

        assertThat(image.getImageName()).isNull();
    }

    @Test
    void getItemIfImageNameIsEmptyTest() {
        Image image = imageService.getImage("");

        assertThat(image.getImageName()).isEmpty();
    }

    @Test
    void getItemIfImageNameIsNullNotThrowingAnyExceptionTest() {
        assertThatNoException().isThrownBy(() -> imageService.getImage(null));
    }

    @Test
    void saveImageIfImageIsNotNullTest() {
        doReturn(image).when(imageRepository).save(any(Image.class));

        Image actual = imageService.save(image);

        assertThat(actual).isEqualTo(image);

        verify(imageRepository, times(1)).save(image);
    }

    @Test
    void saveImageIfImageIsNullTest() {
        Image nullImage = null;
        when(imageRepository.save(nullImage)).thenThrow(NullPointerException.class);

        assertThatNullPointerException().isThrownBy(() -> imageService.save(nullImage));

        verify(imageRepository, times(1)).save(nullImage);
    }

    @Test
    void deleteImageExistImageTest() {
        doNothing().when(imageRepository).delete(image);

        imageService.delete(image);

        verify(imageRepository, times(1)).delete(image);
    }

    @Test
    void deleteImageIfImageIsNullTest() {
        doThrow(IllegalArgumentException.class).when(imageRepository).delete(null);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> imageService.delete(null));
    }

    @ParameterizedTest
    @MethodSource("getUploadImageParamsTest")
    void uploadImageParameterizedTest(MultipartFile partFile, String bucketName, String imageName) {
        String actual = "";
        try {
            doReturn(imageName).when(imageUploader).uploadImage(partFile, bucketName, imageName);
            actual = imageService.uploadImage(partFile, bucketName, imageName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).isEqualTo(imageName);
    }

    static Stream<Arguments> getUploadImageParamsTest() {
        MultipartFile multipartFile = mock(MultipartFile.class);
        return Stream.of(
                of(multipartFile, BucketName.CLOTHES.getName(), "test.img"),
                of(multipartFile, null, "test.img"),
                of(multipartFile, BucketName.CLOTHES.getName(), null),
                of(multipartFile, null, null),
                of(multipartFile, "", "")
        );
    }

    @Test
    void uploadImageIfMultipartIsNull() {
        try {
            doThrow(IOException.class).when(imageUploader).uploadImage(null, BucketName.CLOTHES.getName(), "test.img");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> imageService.uploadImage(null, BucketName.CLOTHES.getName(), "test.img"));
    }
}
