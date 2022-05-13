package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.BucketName;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.service.ImageService;
import by.zhigalko.snowworld.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminItemServiceImplTest {
    private Item item;
    private Image image;
    private ItemRequest itemRequest;

    @Mock
    private ImageService imageService;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private AdminItemServiceImpl adminItemService;

    @BeforeEach
    void setUp() {
        itemRequest = new ItemRequest();
        itemRequest.setCost("50.0");
        itemRequest.setAvailableToRental("true");
        itemRequest.setProductName(String.valueOf(Product.SNOWBOARD));
        itemRequest.setMaker("MAKER");
        image = new Image();
        image.setImageName("image.jpg");
        image.setId(UUID.randomUUID());
        item = new Item();
        image.addItem(item);
        item.setCost(Double.parseDouble(itemRequest.getCost()));
        item.setAvailableToRental(Boolean.parseBoolean(itemRequest.getAvailableToRental()));
        item.setProductName(Product.valueOf(itemRequest.getProductName()));
        item.setMaker(itemRequest.getMaker());
    }

    @Test
    void addNewItemIfParametersAreNotNullTest() throws IOException {
        doReturn("image.jpg").when(imageService).uploadImage(multipartFile, BucketName.SNOWBOARD.getName().toLowerCase());
        doReturn(image).when(imageService).getImage("image.jpg");
        doReturn(item).when(itemService).getItem(itemRequest, image);
        doReturn(item).when(itemService).save(item);
        doReturn(image).when(imageService).save(image);

        boolean added = adminItemService.addNewItem(itemRequest, multipartFile, String.valueOf(Product.SNOWBOARD).toLowerCase());

        assertThat(added).isTrue();

        verify(imageService).uploadImage(multipartFile, BucketName.SNOWBOARD.getName().toLowerCase());
        verify(imageService).getImage("image.jpg");
        verify(itemService).getItem(itemRequest, image);
        verify(itemService).save(item);
        verify(imageService).save(image);
    }

    @Test
    void addNewItemIfImageNotUploadedAndThrowingExceptionTest() throws IOException {
        doThrow(IOException.class).when(imageService).uploadImage(null, "dummy");

        assertThatIOException().isThrownBy(() -> adminItemService.addNewItem(itemRequest, null, "dummy"));
    }

    @Test
    void addNewItemIfParamsAreNullTest() throws IOException {
        assertThatNullPointerException().isThrownBy(() -> adminItemService.addNewItem(null, null, null));
    }

    @Test
    void deleteItemIfIdIsNotNullAndItemIsExistTest() {
        doReturn(item).when(itemService).findById(item.getId());
        doNothing().when(itemService).delete(item);
        doNothing().when(imageService).delete(image);

        adminItemService.deleteItem(item.getId());

        verify(itemService).findById(item.getId());
        verify(itemService).delete(item);
        verify(imageService).delete(image);
    }

    @Test
    void deleteItemIfIdIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> adminItemService.deleteItem(null));
    }

    @ParameterizedTest
    @MethodSource("getUpdateParams")
    void updateItemIfParamsNotNullParameterizedTest(String cost, String availableToRental) {
        doReturn(item).when(itemService).findById(item.getId());
        doReturn(item).when(itemService).save(item);

        boolean updated = adminItemService.updateItem(cost, availableToRental, item.getId());

        assertThat(updated).isTrue();

        verify(itemService).findById(item.getId());
        verify(itemService).save(item);
    }

    static Stream<Arguments> getUpdateParams() {
        return Stream.of(
                of("15.0", String.valueOf(false)),
                of("", ""),
                of("15.0", ""),
                of("", "false")
        );
    }

    @Test
    void updateItemParamsAreNullTest() {
        assertThatNullPointerException().isThrownBy(()-> adminItemService.updateItem(null,null, item.getId()));
    }

    @Test
    void updateItemIfItemNotFoundTest() {
        assertThatNullPointerException().isThrownBy(() -> adminItemService.updateItem("15.0", "false", UUID.randomUUID()));
    }
}
