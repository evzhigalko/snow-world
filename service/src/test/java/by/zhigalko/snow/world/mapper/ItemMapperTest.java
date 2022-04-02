package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import by.zhigalko.snow.world.util.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Test
    void itemDtoToItemTest() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(UUID.randomUUID());
        itemRequest.setCost(String.valueOf(15.0));
        itemRequest.setGender("MALE");
        itemRequest.setMaker("BURTON");
        itemRequest.setAvailableToRental("true");
        itemRequest.setProductName("SNOWBOARD");
        itemRequest.setImageName("IMAGE_NAME_TEST.jpg");
        itemRequest.setEquipmentSize("SN159");

        Item item = itemMapper.itemDtoToItem(itemRequest);

        assertNotNull(item);
        assertEquals(itemRequest.getEquipmentSize(), item.getEquipmentSize().getEquipmentSizeId());
        assertEquals(itemRequest.getImageName(), item.getImage().getImageName());
        assertEquals(itemRequest.getId(), item.getId());

        System.out.println(itemRequest);
        System.out.println(item);

    }

    @Test
    void itemToItemDtoTest() {
        Item item= new Item();
        item.setId(UUID.randomUUID());
        item.setCost(15.0);
        item.setGender(Gender.MALE);
        item.setMaker("BURTON");
        item.setAvailableToRental(true);
        item.setProductName(Product.SNOWBOARD);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        item.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SN159");
        item.setEquipmentSize(equipmentSize);

        ItemResponse itemResponse = itemMapper.itemToItemDto(item);

        assertNotNull(itemResponse);
        assertEquals(item.getEquipmentSize().getEquipmentSizeId(), itemResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(item.getImage().getImageName(), item.getImage().getImageName());
        assertEquals(item.getId(), itemResponse.getId());

        System.out.println(item);
        System.out.println(itemResponse);
    }
}