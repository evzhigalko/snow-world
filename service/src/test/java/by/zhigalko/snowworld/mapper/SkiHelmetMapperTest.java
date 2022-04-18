package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiHelmetRequest;
import by.zhigalko.snowworld.dto.response.SkiHelmetResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiHelmet;
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
        Assertions.assertEquals(skiHelmetRequest.getProductName(), skiHelmet.getProductName().toString());
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
