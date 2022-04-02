package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.MittenRequest;
import by.zhigalko.snow.world.dto.item.response.MittenResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
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
class MittenMapperTest {
    @Autowired
    private MittenMapper mittenMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Test
    void mittenRequestToMittenTest() {
        MittenRequest mittenRequest = new MittenRequest();
        mittenRequest.setMembrane("15000");
        mittenRequest.setCost("15");
        mittenRequest.setAvailableToRental("true");
        mittenRequest.setGender("FEMALE");
        mittenRequest.setEquipmentSize("XL");
        mittenRequest.setMaker("QUIKSILVER");
        mittenRequest.setProductName("MITTEN");

        Mitten mitten = mittenMapper.mittenRequestToMitten(mittenRequest);

        assertNotNull(mitten);
        assertEquals(mittenRequest.getEquipmentSize(), mitten.getEquipmentSize().getEquipmentSizeId());
        assertEquals(mittenRequest.getMaker(), mitten.getMaker());
        assertEquals(mittenRequest.getProductName(), mitten.getProductName().toString());
        System.out.println(mittenRequest);
        System.out.println(mitten);
    }

    @Test
    void mittenToMittenResponseTest() {
        Mitten mitten = new Mitten();
        mitten.setMembrane(15000);
        mitten.setCost(15);
        mitten.setAvailableToRental(true);
        mitten.setGender(Gender.FEMALE);
        mitten.setMaker("QUIKSILVER");
        mitten.setProductName(Product.MITTEN);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        mitten.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("XL");
        mitten.setEquipmentSize(equipmentSize);

        MittenResponse mittenResponse = mittenMapper.mittenToMittenResponse(mitten);

        assertNotNull(mitten);
        assertEquals(mitten.getEquipmentSize().getEquipmentSizeId(), mittenResponse.getEquipmentSize().getEquipmentSizeId());
        assertEquals(mitten.getMaker(), mittenResponse.getMaker());
        assertEquals(mitten.getProductName().toString(), mittenResponse.getProductName());
        System.out.println(mitten);
        System.out.println(mittenResponse);
    }
}
