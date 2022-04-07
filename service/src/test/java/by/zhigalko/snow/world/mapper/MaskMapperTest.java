package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.MaskRequest;
import by.zhigalko.snow.world.dto.item.response.MaskResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mask;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.mapper.item.MaskMapper;
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
class MaskMapperTest {

    @Autowired
    private MaskMapper maskMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Test
    void maskRequestToMaskTest() {
        MaskRequest maskRequest = new MaskRequest();
        maskRequest.setCost("15");
        maskRequest.setAvailableToRental("true");
        maskRequest.setGender("FEMALE");
        maskRequest.setEquipmentSize("XL");
        maskRequest.setMaker("QUIKSILVER");
        maskRequest.setProductName("MASK");

        Mask mask = maskMapper.maskRequestToMask(maskRequest);

        assertNotNull(mask);
        assertEquals(maskRequest.getEquipmentSize(), mask.getEquipmentSize().getEquipmentSizeId());
        assertEquals(maskRequest.getMaker(), mask.getMaker());
        assertEquals(maskRequest.getProductName(), mask.getProductName().toString());
        System.out.println(maskRequest);
        System.out.println(mask);
    }

    @Test
    void maskToMaskResponseTest() {
        Mask mask = new Mask();
        mask.setCost(15);
        mask.setId(UUID.randomUUID());
        mask.setAvailableToRental(true);
        mask.setGender(Gender.FEMALE);
        mask.setMaker("QUIKSILVER");
        mask.setProductName(Product.MASK);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        mask.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        mask.setEquipmentSize(equipmentSize);

        MaskResponse maskResponse = maskMapper.maskToMaskResponse(mask);

        assertNotNull(mask);
        assertEquals(mask.getEquipmentSize().getEquipmentSizeId(), maskResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(mask.getMaker(), maskResponse.getMaker());
        assertEquals(mask.getProductName().toString(), maskResponse.getProductName());
        System.out.println(mask);
        System.out.println(maskResponse);
    }
}
