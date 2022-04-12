package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.SkiHelmetRequest;
import by.zhigalko.snow.world.dto.response.SkiHelmetResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.model.Gender;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.entity.SkiHelmet;
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
class SkiHelmetMapperTest {

    @Autowired
    private SkiHelmetMapper skiHelmetMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void skiHelmetRequestToSkiHelmetTest() {
        SkiHelmetRequest skiHelmetRequest = new SkiHelmetRequest();
        skiHelmetRequest.setCost("15.0");
        skiHelmetRequest.setGender("MALE");
        skiHelmetRequest.setMaker("PRIME");
        skiHelmetRequest.setProductName("SKI_HELMET");
        skiHelmetRequest.setAvailableToRental("true");
        skiHelmetRequest.setEquipmentSize("L");

        SkiHelmet skiHelmet = skiHelmetMapper.skiHelmetRequestToSkiHelmet(skiHelmetRequest);

        assertNotNull(skiHelmet);
        assertEquals(skiHelmetRequest.getEquipmentSize(), skiHelmet.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiHelmetRequest.getMaker(), skiHelmet.getMaker());
        assertEquals(skiHelmetRequest.getProductName(), skiHelmet.getProductName().toString());
        System.out.println(skiHelmetRequest);
        System.out.println(skiHelmet);
    }

    @Test
    void skiHelmetToSkiHelmetResponseTest() {
        SkiHelmet skiHelmet = new SkiHelmet();
        skiHelmet.setId(UUID.randomUUID());
        skiHelmet.setCost(15.0);
        skiHelmet.setGender(Gender.FEMALE);
        skiHelmet.setMaker("PRIME");
        skiHelmet.setProductName(Product.SKI_HELMET);
        skiHelmet.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        skiHelmet.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("L");
        skiHelmet.setEquipmentSize(equipmentSize);

        SkiHelmetResponse skiHelmetResponse = skiHelmetMapper.skiHelmetToSkiHelmetResponse(skiHelmet);

        assertNotNull(skiHelmetResponse);
        assertEquals(skiHelmet.getEquipmentSize().getEquipmentSizeId(), skiHelmetResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiHelmet.getId(), skiHelmetResponse.getId());
        System.out.println(skiHelmet);
        System.out.println(skiHelmetResponse);
    }
}
