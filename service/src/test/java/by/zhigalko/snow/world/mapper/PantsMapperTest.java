package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.PantsRequest;
import by.zhigalko.snow.world.dto.response.PantsResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Pants;
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
class PantsMapperTest {
    @Autowired
    private PantsMapper pantsMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void pantsRequestToPantsTest() {
        PantsRequest pantsRequest = new PantsRequest();
        pantsRequest.setMembrane("15000");
        pantsRequest.setCost("15");
        pantsRequest.setAvailableToRental("true");
        pantsRequest.setGender("FEMALE");
        pantsRequest.setEquipmentSize("XL");
        pantsRequest.setMaker("QUIKSILVER");
        pantsRequest.setProductName("JACKET");

        Pants pants = pantsMapper.pantsRequestToPants(pantsRequest);

        assertNotNull(pants);
        assertEquals(pantsRequest.getEquipmentSize(), pants.getEquipmentSize().getEquipmentSizeId());
        assertEquals(pantsRequest.getMaker(), pants.getMaker());
        assertEquals(pantsRequest.getProductName(), pants.getProductName().toString());
        System.out.println(pantsRequest);
        System.out.println(pants);
    }

    @Test
    void pantsToPantsResponseTest() {
        Pants pants = new Pants();
        pants.setMembrane(15000);
        pants.setCost(15);
        pants.setAvailableToRental(true);
        pants.setGender(Gender.FEMALE);
        pants.setMaker("QUIKSILVER");
        pants.setProductName(Product.PANTS);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        pants.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        pants.setEquipmentSize(equipmentSize);

        PantsResponse pantsResponse = pantsMapper.pantsToPantsResponse(pants);

        assertNotNull(pants);
        assertEquals(pants.getEquipmentSize().getEquipmentSizeId(), pantsResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(pants.getMaker(), pantsResponse.getMaker());
        assertEquals(pants.getProductName().toString(), pantsResponse.getProductName());
        System.out.println(pantsResponse);
        System.out.println(pants);
    }
}