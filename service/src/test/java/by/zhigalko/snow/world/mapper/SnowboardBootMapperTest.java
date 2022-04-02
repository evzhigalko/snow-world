package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SnowboardBootRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.item.response.SnowboardBootResponse;
import by.zhigalko.snow.world.dto.item.response.SnowboardResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
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
class SnowboardBootMapperTest {

    @Autowired
    private SnowboardBootMapper snowboardBootMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

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
        assertEquals(snowboardBootRequest.getLacingSystem(), snowboardBoot.getLacingSystem().toString());
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
        assertEquals(snowboardBootResponse.getEquipmentSize().getEquipmentSizeId(), snowboardBootResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(snowboardBoot.getId(), snowboardBootResponse.getId());
        System.out.println(snowboardBoot);
        System.out.println(snowboardBootResponse);
    }
}