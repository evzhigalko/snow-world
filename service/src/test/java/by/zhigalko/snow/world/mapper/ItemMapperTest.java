package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.ItemDto;
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
        ItemDto itemDto = new ItemDto();
        itemDto.setId("d5ffc460-649f-4ee0-a31b-af8e151e8351");
        itemDto.setCost(String.valueOf(15.0));
        itemDto.setGender("MALE");
        itemDto.setMaker("BURTON");
        itemDto.setAvailableToRental("true");
        itemDto.setProductName("SNOWBOARD");
        Image image = new Image();
        image.setImageName("IMAGE_NAME_TEST.jpg");
        itemDto.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SN159");
        itemDto.setEquipmentSize(equipmentSize);

        Item item = itemMapper.itemDtoToItem(itemDto);

        assertNotNull(item);
        assertEquals(itemDto.getEquipmentSize(), item.getEquipmentSize());
        assertEquals(UUID.fromString(itemDto.getId()), item.getId());
        System.out.println(itemDto);
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
        image.setImageName("IMAGE_NAME_TEST.jpg");
        item.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SN159");
        item.setEquipmentSize(equipmentSize);

        ItemDto itemDto = itemMapper.itemToItemDto(item);

        assertNotNull(itemDto);
        assertEquals(item.getEquipmentSize(), itemDto.getEquipmentSize());
        assertEquals(item.getId(), UUID.fromString(itemDto.getId()));
        System.out.println(item);
        System.out.println(itemDto);
    }
}