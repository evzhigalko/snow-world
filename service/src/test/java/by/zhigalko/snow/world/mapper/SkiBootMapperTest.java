package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SkiBootRequest;
import by.zhigalko.snow.world.dto.item.response.SkiBootResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
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
class SkiBootMapperTest {

    @Autowired
    private SkiBootMapper skiBootMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;


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
        assertEquals(skiBootRequest.getProductName(), skiBoot.getProductName().toString());
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
