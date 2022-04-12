package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.CapRequest;
import by.zhigalko.snow.world.dto.response.CapResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Cap;
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
class CapMapperTest {
    @Autowired
    private CapMapper capMapper;

    @Autowired
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Test
    void capRequestToCapTest() {
        CapRequest capRequest = new CapRequest();
        capRequest.setCost("15");
        capRequest.setAvailableToRental("true");
        capRequest.setGender("FEMALE");
        capRequest.setEquipmentSize("XL");
        capRequest.setMaker("QUIKSILVER");
        capRequest.setProductName("CAP");

        Cap cap = capMapper.capRequestToCap(capRequest);

        assertNotNull(cap);
        assertEquals(capRequest.getEquipmentSize(), cap.getEquipmentSize().getEquipmentSizeId());
        assertEquals(capRequest.getMaker(), cap.getMaker());
        assertEquals(capRequest.getProductName(), cap.getProductName().toString());
        System.out.println(capRequest);
        System.out.println(cap);
    }

    @Test
    void capToCapResponseTest() {
        Cap cap = new Cap();
        cap.setCost(15);
        cap.setId(UUID.randomUUID());
        cap.setAvailableToRental(true);
        cap.setGender(Gender.FEMALE);
        cap.setMaker("QUIKSILVER");
        cap.setProductName(Product.CAP);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        cap.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        cap.setEquipmentSize(equipmentSize);

        CapResponse capResponse = capMapper.capToCapResponse(cap);

        assertNotNull(cap);
        assertEquals(cap.getEquipmentSize().getEquipmentSizeId(), capResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(cap.getMaker(), capResponse.getMaker());
        assertEquals(cap.getProductName().toString(), capResponse.getProductName());
        System.out.println(cap);
        System.out.println(capResponse);
    }
}