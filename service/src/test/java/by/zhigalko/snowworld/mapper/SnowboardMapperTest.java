package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SnowboardRequest;
import by.zhigalko.snowworld.dto.response.SnowboardResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.HardnessLevel;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.RidingLevel;
import by.zhigalko.snowworld.entity.Snowboard;
import by.zhigalko.snowworld.service.impl.EquipmentSizeServiceImpl;
import by.zhigalko.snowworld.config.ApplicationConfig;
import org.junit.jupiter.api.Assertions;
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
    private EquipmentSizeServiceImpl equipmentSizeService;

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
        Assertions.assertEquals(snowboardRequest.getHardnessLevel(), snowboard.getHardnessLevel().toString());
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
