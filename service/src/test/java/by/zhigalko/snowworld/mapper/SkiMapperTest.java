package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiRequest;
import by.zhigalko.snowworld.dto.response.SkiResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.model.RidingLevel;
import by.zhigalko.snowworld.entity.Ski;
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
class SkiMapperTest {

    @Autowired
    private SkiMapper skiMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;


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
        Assertions.assertEquals(skiRequest.getRidingLevel(), ski.getRidingLevel().toString());
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
        Assertions.assertEquals(ski.getEquipmentSize().getEquipmentSizeId(), skiResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(ski.getId(), skiResponse.getId());
        System.out.println(ski);
        System.out.println(skiResponse);
    }
}