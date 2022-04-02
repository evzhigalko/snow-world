package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SkiHelmetRequest;
import by.zhigalko.snow.world.dto.item.response.SkiHelmetResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
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
class SkiHelmetMapperTest {

    @Autowired
    private SkiHelmetMapper skiHelmetMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

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
