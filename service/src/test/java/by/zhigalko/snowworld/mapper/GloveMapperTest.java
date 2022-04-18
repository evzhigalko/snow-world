package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.GloveRequest;
import by.zhigalko.snowworld.dto.response.GloveResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Glove;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
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
        Assertions.assertEquals(gloveRequest.getProductName(), glove.getProductName().toString());
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
        Assertions.assertEquals(glove.getProductName().toString(), gloveResponse.getProductName());
        System.out.println(glove);
        System.out.println(gloveResponse);
    }
}
