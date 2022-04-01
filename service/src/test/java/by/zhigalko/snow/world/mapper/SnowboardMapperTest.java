package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.SnowboardDto;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
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
class SnowboardMapperTest {

    @Autowired
    private SnowboardMapper snowboardMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Test
    void snowboardDtoToSnowboardTest() {
        SnowboardDto snowboardDto = new SnowboardDto();
        snowboardDto.setId(String.valueOf(UUID.randomUUID()));
        snowboardDto.setCost("15.0");
        snowboardDto.setGender("FEMALE");
        snowboardDto.setMaker("PRIME");
        snowboardDto.setHardnessLevel("TWO");
        snowboardDto.setRidingLevel("BEGINNER");
        snowboardDto.setProductName("SNOWBOARD");
        snowboardDto.setAvailableToRental("true");
        Image image = new Image();
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboardDto.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SN159");
        snowboardDto.setEquipmentSize(equipmentSize);

        Snowboard snowboard = snowboardMapper.snowboardDtoToSnowboard(snowboardDto);

        assertNotNull(snowboard);
        assertEquals(snowboardDto.getEquipmentSize().getEquipmentSizeId(), snowboard.getEquipmentSize().getEquipmentSizeId());
        assertEquals(UUID.fromString(snowboardDto.getId()), snowboard.getId());
        System.out.println(snowboardDto);
        System.out.println(snowboard);
    }

    @Test
    void snowboardToSnowboardDtoTest() {
        Snowboard snowboard = new Snowboard();
        snowboard.setId(UUID.randomUUID());
        snowboard.setCost(15.0);
        snowboard.setGender(Gender.FEMALE);
        snowboard.setMaker("PRIME");
        snowboard.setHardnessLevel(HardnessLevel.FOUR);
        snowboard.setRidingLevel(RidingLevel.BEGINNER);
        snowboard.setProductName(Product.SNOWBOARD);
        snowboard.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboard.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SN159");
        snowboard.setEquipmentSize(equipmentSize);

        SnowboardDto snowboardDto = snowboardMapper.snowboardToSnowboardDto(snowboard);

        assertNotNull(snowboardDto);
        assertEquals(snowboard.getEquipmentSize().getEquipmentSizeId(), snowboardDto.getEquipmentSize().getEquipmentSizeId());
        assertEquals(String.valueOf(snowboard.getId()), snowboardDto.getId());
        System.out.println(snowboard);
        System.out.println(snowboardDto);
    }
}
