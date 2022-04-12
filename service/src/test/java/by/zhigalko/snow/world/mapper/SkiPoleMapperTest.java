package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.SkiPoleRequest;
import by.zhigalko.snow.world.dto.response.SkiPoleResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.model.Gender;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.entity.SkiPole;
import by.zhigalko.snow.world.service.impl.EquipmentSizeServiceImpl;
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
        assertEquals(skiPoleRequest.getProductName(), skiPole.getProductName().toString());
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
