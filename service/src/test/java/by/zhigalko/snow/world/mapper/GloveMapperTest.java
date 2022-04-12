package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.GloveRequest;
import by.zhigalko.snow.world.dto.response.GloveResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Glove;
import by.zhigalko.snow.world.model.Gender;
import by.zhigalko.snow.world.model.Product;
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
class GloveMapperTest {
    @Autowired
    private GloveMapper gloveMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void gloveRequestToGloveTest() {
        GloveRequest gloveRequest = new GloveRequest();
        gloveRequest.setMembrane("15000");
        gloveRequest.setCost("15");
        gloveRequest.setAvailableToRental("true");
        gloveRequest.setGender("FEMALE");
        gloveRequest.setEquipmentSize("XL");
        gloveRequest.setMaker("QUIKSILVER");
        gloveRequest.setProductName("GLOVE");

        Glove glove = gloveMapper.gloveRequestToGlove(gloveRequest);

        assertNotNull(glove);
        assertEquals(gloveRequest.getEquipmentSize(), glove.getEquipmentSize().getEquipmentSizeId());
        assertEquals(gloveRequest.getMaker(), glove.getMaker());
        assertEquals(gloveRequest.getProductName(), glove.getProductName().toString());
        System.out.println(gloveRequest);
        System.out.println(glove);
    }

    @Test
    void gloveToGloveResponseTest() {
        Glove glove = new Glove();
        glove.setMembrane(15000);
        glove.setCost(15);
        glove.setAvailableToRental(true);
        glove.setGender(Gender.FEMALE);
        glove.setMaker("QUIKSILVER");
        glove.setProductName(Product.GLOVE);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        glove.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        glove.setEquipmentSize(equipmentSize);

        GloveResponse gloveResponse = gloveMapper.gloveToGloveResponse(glove);

        assertNotNull(glove);
        assertEquals(glove.getEquipmentSize().getEquipmentSizeId(), gloveResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(glove.getMaker(), gloveResponse.getMaker());
        assertEquals(glove.getProductName().toString(), gloveResponse.getProductName());
        System.out.println(glove);
        System.out.println(gloveResponse);
    }
}
