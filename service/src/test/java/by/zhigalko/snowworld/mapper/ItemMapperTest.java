package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.HardnessLevel;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.RidingLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemMapperTest {
    @Mock
    private ItemMapper itemMapper;

    @Test
    void itemSetToItemResponseSet() {
        //GIVEN
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setProductName(Product.SNOWBOARD);
        item.setMaker("TEST");

        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("TEST_ID");
        item.setEquipmentSize(equipmentSize);

        Image image = new Image();
        image.setImageName("img.jpg");
        item.setImage(image);

        Map<String, Object> itemInfo = item.getItemInformation();
        itemInfo.put("ridingLevel", RidingLevel.EXPERT.getName());
        itemInfo.put("hardnessLevel", HardnessLevel.THREE.getName());
        item.setItemInformation(itemInfo);

        Item item2 = new Item();
        item2.setId(UUID.randomUUID());
        item2.setProductName(Product.SNOWBOARD_BOOT);
        item2.setMaker("TEST");

        EquipmentSize equipmentSize2 = new EquipmentSize();
        equipmentSize2.setEquipmentSizeId("TEST_ID2");
        item.setEquipmentSize(equipmentSize2);

        Image image2 = new Image();
        image2.setImageName("img.jpg");
        item2.setImage(image2);

        Map<String, Object> itemInfo2 = item2.getItemInformation();
        itemInfo2.put("lacingSystem", HardnessLevel.TWO.getName());
        item2.setItemInformation(itemInfo2);

        Set<Item> expected = Set.of(item, item2);

        doReturn(expected).when(itemMapper).itemSetToItemResponseSet(expected);

        //WHEN
        Set<ItemResponse> actual = itemMapper.itemSetToItemResponseSet(expected);

        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
    }
}
