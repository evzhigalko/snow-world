package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SkiRequest;
import by.zhigalko.snow.world.dto.item.response.SkiResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.mapper.item.SkiMapper;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import by.zhigalko.snow.world.config.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class SkiMapperTest {

    @Autowired
    private SkiMapper skiMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;


    @Test
    void skiRequestToSkiTest() {
        SkiRequest skiRequest = new SkiRequest();
        skiRequest.setCost("15.0");
        skiRequest.setGender("FEMALE");
        skiRequest.setMaker("PRIME");
        skiRequest.setRidingLevel("BEGINNER");
        skiRequest.setProductName("SKI");
        skiRequest.setAvailableToRental("true");
        skiRequest.setEquipmentSize("SK183");

        Ski ski = skiMapper.skiRequestToSki(skiRequest);

        assertNotNull(ski);
        assertEquals(skiRequest.getEquipmentSize(), ski.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiRequest.getRidingLevel(), ski.getRidingLevel().toString());
        System.out.println(skiRequest);
        System.out.println(ski);
    }

    @Test
    void skiToSkiResponseTest() {
        Ski ski = new Ski();
        ski.setId(UUID.randomUUID());
        ski.setCost(15.0);
        ski.setGender(Gender.FEMALE);
        ski.setMaker("PRIME");
        ski.setRidingLevel(RidingLevel.BEGINNER);
        ski.setProductName(Product.SKI);
        ski.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        ski.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SK183");
        ski.setEquipmentSize(equipmentSize);

        SkiResponse skiResponse = skiMapper.skiToSkiResponse(ski);

        assertNotNull(skiResponse);
        assertEquals(ski.getEquipmentSize().getEquipmentSizeId(), skiResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(ski.getId(), skiResponse.getId());
        System.out.println(ski);
        System.out.println(skiResponse);
    }
}