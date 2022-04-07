package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.JacketRequest;
import by.zhigalko.snow.world.dto.item.response.JacketResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.mapper.item.JacketMapper;
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
class JacketMapperTest {
    @Autowired
    private JacketMapper jacketMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

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
        assertEquals(jacketRequest.getProductName(), jacket.getProductName().toString());
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
        assertEquals(jacket.getProductName().toString(), jacketResponse.getProductName());
        System.out.println(jacketResponse);
        System.out.println(jacket);
    }
}
