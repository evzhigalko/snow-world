package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.item.response.SnowboardResponse;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class SnowboardMapperTest {

    @Autowired
    private SnowboardMapper snowboardMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Test
    void snowboardRequestToSnowboardTest() {
        SnowboardRequest snowboardRequest = new SnowboardRequest();
        snowboardRequest.setCost("15.0");
        snowboardRequest.setGender("FEMALE");
        snowboardRequest.setMaker("PRIME");
        snowboardRequest.setHardnessLevel("TWO");
        snowboardRequest.setRidingLevel("BEGINNER");
        snowboardRequest.setProductName("SNOWBOARD");
        snowboardRequest.setAvailableToRental("true");
        snowboardRequest.setEquipmentSize("SN159");

        Snowboard snowboard = snowboardMapper.snowboardRequestToSnowboard(snowboardRequest);

        assertNotNull(snowboard);
        assertEquals(snowboardRequest.getEquipmentSize(), snowboard.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboardRequest.getHardnessLevel(), snowboard.getHardnessLevel().toString());
        System.out.println(snowboardRequest);
        System.out.println(snowboard);
    }

    @Test
    void snowboardToSnowboardResponseTest() {
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

        SnowboardResponse snowboardResponse = snowboardMapper.snowboardToSnowboardResponse(snowboard);

        assertNotNull(snowboardResponse);
        assertEquals(snowboard.getEquipmentSize().getEquipmentSizeId(), snowboardResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboard.getId(), snowboardResponse.getId());
        System.out.println(snowboard);
        System.out.println(snowboardResponse);
    }
}
