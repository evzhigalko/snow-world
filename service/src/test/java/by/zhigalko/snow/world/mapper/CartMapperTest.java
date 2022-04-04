package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.entity.*;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import by.zhigalko.snow.world.service.user.UserService;
import by.zhigalko.snow.world.util.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private EquipmentSizeService equipmentSizeService;

    @Autowired
    private UserService userService;

    @Test
    void cartDtoToCartTest() {
        CartDto cartDto = new CartDto();
        cartDto.setId(userService.findById(UUID.fromString("53178e8d-eb58-411f-b357-c1c89c3349ec")).getId());
        cartDto.setReservationDayNumber(4);
        cartDto.setStartReservationDate(LocalDate.now());
        cartDto.setTotalSum(54.0);
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setId(UUID.randomUUID());
        snowboardHelmet.setCost(15.0);
        snowboardHelmet.setGender(Gender.FEMALE);
        snowboardHelmet.setMaker("PRIME");
        snowboardHelmet.setProductName(Product.SNOWBOARD_HELMET);
        snowboardHelmet.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboardHelmet.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("L");
        snowboardHelmet.setEquipmentSize(equipmentSize);
        Set<Item> items = new HashSet<>();
        cartDto.setItems(items);

        Cart cart = cartMapper.cartDtoToCart(cartDto);

        assertNotNull(cart);
        assertEquals(cartDto.getId(), cart.getId());

        System.out.println(cartDto);
        System.out.println(cart);
    }

    @Test
    void cartToCartDtoTest() {
        Cart cart = new Cart();
        User user = userService.findById(UUID.fromString("53178e8d-eb58-411f-b357-c1c89c3349ec"));
        cart.setId(user.getId());
        cart.setReservationDayNumber(4);
        cart.setStartReservationDate(LocalDate.now());
        cart.setTotalSum(54.0);
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setId(UUID.randomUUID());
        snowboardHelmet.setCost(15.0);
        snowboardHelmet.setGender(Gender.FEMALE);
        snowboardHelmet.setMaker("PRIME");
        snowboardHelmet.setProductName(Product.SNOWBOARD_HELMET);
        snowboardHelmet.setAvailableToRental(true);
        Image image = new Image();
        image.setId(UUID.randomUUID());
        image.setImageName("IMAGE_NAME_TEST.jpg");
        snowboardHelmet.setImage(image);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById("L");
        snowboardHelmet.setEquipmentSize(equipmentSize);
        Set<Item> items = new HashSet<>();
        cart.setItems(items);
        cart.setUser(user);

        CartDto cartDto = cartMapper.cartToCartDto(cart);

        assertNotNull(cartDto);
        assertEquals(cart.getId(),cartDto.getId());
        System.out.println(cart);
        System.out.println(cartDto);
    }
}