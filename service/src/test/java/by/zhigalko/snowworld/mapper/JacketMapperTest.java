package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.JacketRequest;
import by.zhigalko.snowworld.dto.response.JacketResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Jacket;
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
class JacketMapperTest {
    @Autowired
    private JacketMapper jacketMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void jacketRequestToJacketTest() {
        JacketRequest jacketRequest = new JacketRequest();
        jacketRequest.setMembrane("15000");
        jacketRequest.setCost("15");
        jacketRequest.setAvailableToRental("true");
        jacketRequest.setGender("FEMALE");
        jacketRequest.setEquipmentSize("XL");
        jacketRequest.setMaker("QUIKSILVER");
        jacketRequest.setProductName("JACKET");

        Jacket jacket = jacketMapper.jacketRequestToJacket(jacketRequest);

        assertNotNull(jacket);
        assertEquals(jacketRequest.getEquipmentSize(), jacket.getEquipmentSize().getEquipmentSizeId());
        assertEquals(jacketRequest.getMaker(), jacket.getMaker());
        Assertions.assertEquals(jacketRequest.getProductName(), jacket.getProductName().toString());
        System.out.println(jacketRequest);
        System.out.println(jacket);
    }

    @Test
    void jacketToJacketResponseTest() {
        Jacket jacket = new Jacket();
        jacket.setMembrane(15000);
        jacket.setCost(15);
        jacket.setAvailableToRental(true);
        jacket.setGender(Gender.FEMALE);
        jacket.setMaker("QUIKSILVER");
        jacket.setProductName(Product.JACKET);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        jacket.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        jacket.setEquipmentSize(equipmentSize);

        JacketResponse jacketResponse = jacketMapper.jacketToJacketResponse(jacket);

        assertNotNull(jacket);
        assertEquals(jacket.getEquipmentSize().getEquipmentSizeId(), jacketResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(jacket.getMaker(), jacketResponse.getMaker());
        Assertions.assertEquals(jacket.getProductName().toString(), jacketResponse.getProductName());
        System.out.println(jacketResponse);
        System.out.println(jacket);
    }
}
