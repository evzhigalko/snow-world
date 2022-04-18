package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SnowboardBootRequest;
import by.zhigalko.snowworld.dto.response.SnowboardBootResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.SnowboardBoot;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.LacingSystem;
import by.zhigalko.snowworld.model.Product;
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
class SnowboardBootMapperTest {

    @Autowired
    private SnowboardBootMapper snowboardBootMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void snowboardBootRequestToSnowboardBootTest() {
        SnowboardBootRequest snowboardBootRequest = new SnowboardBootRequest();
        snowboardBootRequest.setCost("15.0");
        snowboardBootRequest.setGender("FEMALE");
        snowboardBootRequest.setMaker("PRIME");
        snowboardBootRequest.setProductName("SNOWBOARD_BOOT");
        snowboardBootRequest.setAvailableToRental("true");
        snowboardBootRequest.setEquipmentSize("43");
        snowboardBootRequest.setLacingSystem("BOA");

        SnowboardBoot snowboardBoot = snowboardBootMapper.snowboardBootRequestToSnowboardBoot(snowboardBootRequest);

        assertNotNull(snowboardBoot);
        assertEquals(snowboardBootRequest.getEquipmentSize(), snowboardBoot.getEquipmentSize().getEquipmentSizeId());
        Assertions.assertEquals(snowboardBootRequest.getLacingSystem(), snowboardBoot.getLacingSystem().toString());
        assertEquals(snowboardBootRequest.getMaker(), snowboardBoot.getMaker());
        System.out.println(snowboardBootRequest);
        System.out.println(snowboardBoot);
    }

    @Test
    void snowboardToSnowboardBootResponseTest() {
        SnowboardBoot snowboardBoot = new SnowboardBoot();
        snowboardBoot.setId(UUID.randomUUID());
        snowboardBoot.setCost(15.0);
        snowboardBoot.setGender(Gender.FEMALE);
        snowboardBoot.setMaker("PRIME");
        snowboardBoot.setLacingSystem(LacingSystem.LACES);
        snowboardBoot.setProductName(Product.SNOWBOARD_BOOT);
        snowboardBoot.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboardBoot.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("43");
        snowboardBoot.setEquipmentSize(equipmentSize);

        SnowboardBootResponse snowboardBootResponse = snowboardBootMapper.snowboardToSnowboardBootResponse(snowboardBoot);

        assertNotNull(snowboardBootResponse);
        Assertions.assertEquals(snowboardBootResponse.getEquipmentSize().getEquipmentSizeId(), snowboardBootResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboardBoot.getId(), snowboardBootResponse.getId());
        System.out.println(snowboardBoot);
        System.out.println(snowboardBootResponse);
    }
}