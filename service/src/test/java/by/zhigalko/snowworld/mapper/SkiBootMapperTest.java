package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiBootRequest;
import by.zhigalko.snowworld.dto.response.SkiBootResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiBoot;
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
class SkiBootMapperTest {

    @Autowired
    private SkiBootMapper skiBootMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;


    @Test
    void skiBootRequestToSkiBootTest() {
        SkiBootRequest skiBootRequest = new SkiBootRequest();
        skiBootRequest.setCost("15.0");
        skiBootRequest.setGender("MALE");
        skiBootRequest.setMaker("PRIME");
        skiBootRequest.setProductName("SKI_BOOT");
        skiBootRequest.setAvailableToRental("true");
        skiBootRequest.setEquipmentSize("42");

        SkiBoot skiBoot = skiBootMapper.skiBootRequestToSkiBoot(skiBootRequest);

        assertNotNull(skiBoot);
        assertEquals(skiBootRequest.getEquipmentSize(), skiBoot.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiBootRequest.getMaker(), skiBoot.getMaker());
        Assertions.assertEquals(skiBootRequest.getProductName(), skiBoot.getProductName().toString());
        System.out.println(skiBootRequest);
        System.out.println(skiBoot);
    }

    @Test
    void skiBootToSkiBootResponseTest() {
        SkiBoot skiBoot = new SkiBoot();
        skiBoot.setId(UUID.randomUUID());
        skiBoot.setCost(15.0);
        skiBoot.setGender(Gender.FEMALE);
        skiBoot.setMaker("PRIME");
        skiBoot.setProductName(Product.SKI_BOOT);
        skiBoot.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        skiBoot.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("39");
        skiBoot.setEquipmentSize(equipmentSize);

        SkiBootResponse skiBootResponse = skiBootMapper.skiBootToSkiBootResponse(skiBoot);

        assertNotNull(skiBootResponse);
        assertEquals(skiBoot.getEquipmentSize().getEquipmentSizeId(), skiBootResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(skiBoot.getId(), skiBootResponse.getId());
        System.out.println(skiBoot);
        System.out.println(skiBootResponse);
    }
}
