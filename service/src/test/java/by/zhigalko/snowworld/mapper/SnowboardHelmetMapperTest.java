package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SnowboardHelmetRequest;
import by.zhigalko.snowworld.dto.response.SnowboardHelmetResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SnowboardHelmet;
import by.zhigalko.snowworld.service.impl.EquipmentSizeServiceImpl;
import by.zhigalko.snowworld.config.ApplicationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class SnowboardHelmetMapperTest {

    @Autowired
    private SnowboardHelmetMapper snowboardHelmetMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void snowboardHelmetRequestToSnowboardHelmetTest() {
        SnowboardHelmetRequest snowboardHelmetRequest = new SnowboardHelmetRequest();
        snowboardHelmetRequest.setCost("15.0");
        snowboardHelmetRequest.setGender("FEMALE");
        snowboardHelmetRequest.setMaker("PRIME");
        snowboardHelmetRequest.setProductName("SNOWBOARD_HELMET");
        snowboardHelmetRequest.setAvailableToRental("true");
        snowboardHelmetRequest.setEquipmentSize("L");

        SnowboardHelmet snowboardHelmet = snowboardHelmetMapper.snowboardHelmetRequestToSnowboardHelmet(snowboardHelmetRequest);

        assertNotNull(snowboardHelmet);
        assertEquals(snowboardHelmetRequest.getEquipmentSize(), snowboardHelmet.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboardHelmetRequest.getMaker(), snowboardHelmet.getMaker());
        Assertions.assertEquals(snowboardHelmetRequest.getGender(), snowboardHelmet.getGender().toString());
        System.out.println(snowboardHelmetRequest);
        System.out.println(snowboardHelmet);
    }

    @Test
    void snowboardToSnowboardHelmetResponseTest() {
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setId(UUID.randomUUID());
        snowboardHelmet.setCost(15.0);
        snowboardHelmet.setGender(Gender.FEMALE);
        snowboardHelmet.setMaker("PRIME");
        snowboardHelmet.setProductName(Product.SNOWBOARD_HELMET);
        snowboardHelmet.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboardHelmet.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("L");
        snowboardHelmet.setEquipmentSize(equipmentSize);

        SnowboardHelmetResponse snowboardHelmetResponse = snowboardHelmetMapper.snowboardToSnowboardHelmetResponse(snowboardHelmet);

        assertNotNull(snowboardHelmetResponse);
        assertEquals(snowboardHelmetResponse.getEquipmentSize().getEquipmentSizeId(), snowboardHelmetResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboardHelmet.getId(), snowboardHelmetResponse.getId());
        System.out.println(snowboardHelmet);
        System.out.println(snowboardHelmetResponse);
    }
}