package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.mapper.ItemMapper;
import by.zhigalko.snowworld.model.HardnessLevel;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.ProductGroup;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    private Item item;
    private Image image;
    private ItemRequest itemRequest;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private EquipmentSizeRepository equipmentSizeRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        itemRequest = new ItemRequest();
        itemRequest.setCost("50.0");
        itemRequest.setAvailableToRental("true");
        itemRequest.setProductName(String.valueOf(Product.SNOWBOARD));
        itemRequest.setMaker("MAKER");
        image = new Image();
        image.setImageName("test");
        image.setId(UUID.randomUUID());
        item = new Item();
        item.setCost(Double.parseDouble(itemRequest.getCost()));
        item.setAvailableToRental(Boolean.parseBoolean(itemRequest.getAvailableToRental()));
        item.setProductName(Product.valueOf(itemRequest.getProductName()));
        item.setImage(image);
        item.setMaker(itemRequest.getMaker());
    }

    @Test
    void getItemIfItemRequestAndImageAreNotNullTest() {
        doReturn(item).when(itemMapper).itemRequestToItem(itemRequest);

        Item actual = itemService.getItem(itemRequest, image);

        assertThat(actual).isEqualTo(item);
    }

    @Test
    void getItemIfItemRequestIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> itemService.getItem(null, image));
    }

    @Test
    void getItemIfImageIsNullItemShouldReturnNotNullTest() {
        doReturn(item).when(itemMapper).itemRequestToItem(itemRequest);

        Item item = itemService.getItem(itemRequest, null);

        assertThat(item).isNotNull();
    }

    @Test
    void getItemIfImageIsNullNoExceptionTest() {
        doReturn(item).when(itemMapper).itemRequestToItem(itemRequest);

        itemService.getItem(itemRequest, null);

        assertThatNoException().isThrownBy(()-> itemService.getItem(itemRequest, null));
    }

    @Test
    void getItemIfItemInformationIsNotNullTest() {
        Map<String, Object> itemInformation = new HashMap<>();
        itemInformation.put("hardnessLevel", HardnessLevel.EIGHT.getName());
        item.setItemInformation(itemInformation);

        doReturn(item).when(itemMapper).itemRequestToItem(itemRequest);

        Item actual = itemService.getItem(itemRequest, image);

        assertThat(actual.getItemInformation().get("hardnessLevel")).isEqualTo(HardnessLevel.EIGHT.getName());
    }

    @Test
    void saveItemIfItemNotNullTest() {
        doReturn(item).when(itemRepository).save(item);

        Item actual = itemService.save(item);

        assertThat(actual).isEqualTo(item);
    }

    @Test
    void saveITemIfItemIsNullTest() {
        doThrow(NullPointerException.class).when(itemRepository).save(null);

        assertThatNullPointerException().isThrownBy(() -> itemService.save(null));

        verify(itemRepository).save(null);
    }

    @Test
    void findAllIfAllParamsAreExistTest() {
        Page<Item> pages = new PageImpl<>(List.of(item), PageRequest.of(0, 6),3);
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setCost(String.valueOf(item.getCost()));
        itemResponse.setProductName(item.getProductName());
        itemResponse.setAvailableToRental(String.valueOf(item.isAvailableToRental()));
        itemResponse.setMaker(item.getMaker());
        Set<ItemResponse> itemResponseSet = Set.of(itemResponse);

        doReturn(pages).when(itemRepository).findAllByProductName(Product.SNOWBOARD, PageRequest.of(0, 6));
        doReturn(itemResponseSet).when(itemMapper).itemSetToItemResponseSet(Set.of(item));

        Set<ItemResponse> actualResponseSet = itemService.findAll(Product.SNOWBOARD, 0, 6);

        assertThat(actualResponseSet).isEqualTo(itemResponseSet);

        verify(itemRepository).findAllByProductName(Product.SNOWBOARD, PageRequest.of(0, 6));
        verify(itemMapper).itemSetToItemResponseSet(Set.of(item));
    }

    @ParameterizedTest
    @MethodSource("getFindAllParams")
    void findAllParameterizedTest(Product product, int page, int pageSize) {
        Page<Item> pages = new PageImpl<>(List.of(item), PageRequest.of(page, pageSize),3);
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setCost(String.valueOf(item.getCost()));
        itemResponse.setProductName(item.getProductName());
        itemResponse.setAvailableToRental(String.valueOf(item.isAvailableToRental()));
        itemResponse.setMaker(item.getMaker());
        Set<ItemResponse> itemResponseSet = Set.of(itemResponse);

        doReturn(pages).when(itemRepository).findAllByProductName(product, PageRequest.of(page, pageSize));
        doReturn(itemResponseSet).when(itemMapper).itemSetToItemResponseSet(Set.of(item));

        Set<ItemResponse> actualResponseSet = itemService.findAll(product, page, pageSize);

        assertThat(actualResponseSet).isEqualTo(itemResponseSet);

        verify(itemRepository).findAllByProductName(product, PageRequest.of(page, pageSize));
        verify(itemMapper).itemSetToItemResponseSet(Set.of(item));
    }

    static Stream<Arguments> getFindAllParams() {
        return Stream.of(
                of(Product.SNOWBOARD, 0, 6),
                of(null, 0, 6)
        );
    }

    @Test
    void findAllIfProductIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> itemService.findAll(null, 0 , 1));

        verify(itemRepository).findAllByProductName(null, PageRequest.of(0, 1));
    }

    @Test
    void findAllIfProductIfPageEqualsOrLessThanPageSizeTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> itemService.findAll(null, 0 , 0));
    }

    @Test
    void findByIdIfIdIsNotNullTest() {
        doReturn(item).when(itemRepository).getById(item.getId());

        Item actual = itemService.findById(item.getId());

        assertThat(actual).isEqualTo(item);

        verify(itemRepository).getById(item.getId());
    }

    @Test
    void findByIdIfIdIsNullTest() {
        doThrow(NullPointerException.class).when(itemRepository).getById(null);

        assertThatNullPointerException().isThrownBy(() -> itemService.findById(null));

        verify(itemRepository).getById(item.getId());
    }

    @Test
    void deleteIfItemIsNotNullTest() {
        doNothing().when(itemRepository).deleteById(item.getId());

        itemService.delete(item);

        verify(itemRepository).deleteById(item.getId());
    }

    @Test
    void deleteIfItemIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> itemService.delete(null));
    }

    @Test
    void findAllByProductGroupIfProductGroupIsNotNullTest() {
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setProductGroup(ProductGroup.SNOWBOARD);
        equipmentSize.setEquipmentSizeId("SN138");
        equipmentSize.setUserMinHeight(165);
        equipmentSize.setUserMaxHeight(180);
        List<EquipmentSize> sizeList = List.of(equipmentSize);

        doReturn(sizeList).when(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(ProductGroup.SNOWBOARD);

        List<EquipmentSize> actualSizesList = itemService.findAllByProductGroup(ProductGroup.SNOWBOARD);

        assertThat(actualSizesList).isEqualTo(sizeList);

        verify(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(ProductGroup.SNOWBOARD);
    }

    @Test
    void findAllByProductGroupIfProductGroupIsNullTest() {
        doThrow(NullPointerException.class).when(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(null);

        assertThatNullPointerException().isThrownBy(()-> itemService.findAllByProductGroup(null));

        verify(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(null);
    }

    @Test
    void findAllByProductGroupIfProductNotExistShouldReturnEmptyListTest() {
        doReturn(new ArrayList<EquipmentSize>()).when(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(any(ProductGroup.class));

        List<EquipmentSize> actualSizesList = itemService.findAllByProductGroup(ProductGroup.SNOWBOARD);

        assertThat(actualSizesList).isEmpty();

        verify(equipmentSizeRepository).findAllByProductGroupOrderByEquipmentSizeId(ProductGroup.SNOWBOARD);
    }

    @Test
    void findAllByProductGroupIfProductNotExistAndShouldNotThrowingAnyExceptionTest() {
        assertThatNoException().isThrownBy(() -> itemService.findAllByProductGroup(any(ProductGroup.class)));
    }
}
