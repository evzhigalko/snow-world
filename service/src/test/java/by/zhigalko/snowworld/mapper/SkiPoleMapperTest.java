package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiPoleRequest;
import by.zhigalko.snowworld.dto.response.SkiPoleResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiPole;
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
class SkiPoleMapperTest {

    @Autowired
    private SkiPoleMapper skiPoleMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void skiPoleRequestToSkiPoleTest() {
        SkiPoleRequest skiPoleRequest = new SkiPoleRequest();
        skiPoleRequest.setCost("15.0");
        skiPoleRequest.setGender("FEMALE");
        skiPoleRequest.setMaker("PRIME");
        skiPoleRequest.setProductName("SKI_POLE");
        skiPoleRequest.setAvailableToRental("true");
        skiPoleRequest.setEquipmentSize("SP100");

        SkiPole skiPole = skiPoleMapper.skiPoleRequestToSkiPole(skiPoleRequest);

        assertNotNull(skiPole);
        assertEquals(skiPoleRequest.getEquipmentSize(), skiPole.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiPoleRequest.getMaker(), skiPole.getMaker());
        Assertions.assertEquals(skiPoleRequest.getProductName(), skiPole.getProductName().toString());
        System.out.println(skiPoleRequest);
        System.out.println(skiPole);
    }

    @Test
    void skiPoleToSKiPoleResponseTest() {
        SkiPole skiPole = new SkiPole();
        skiPole.setId(UUID.randomUUID());
        skiPole.setCost(15.0);
        skiPole.setGender(Gender.FEMALE);
        skiPole.setMaker("PRIME");
        skiPole.setProductName(Product.SKI);
        skiPole.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        skiPole.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("SP100");
        skiPole.setEquipmentSize(equipmentSize);

        SkiPoleResponse skiPoleResponse = skiPoleMapper.skiPoleToSKiPoleResponse(skiPole);

        assertNotNull(skiPoleResponse);
        assertEquals(skiPole.getEquipmentSize().getEquipmentSizeId(), skiPoleResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiPole.getId(), skiPoleResponse.getId());
        System.out.println(skiPole);
        System.out.println(skiPoleResponse);
    }
}
