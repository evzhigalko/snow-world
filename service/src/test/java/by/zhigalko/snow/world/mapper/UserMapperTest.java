package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.util.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.time.LocalDate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void userRequestToUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(UUID.randomUUID());
        userRequest.setUsername("test");
        userRequest.setEmail("test@gmail.com");
        userRequest.setFirstName("Alex");
        userRequest.setLastName("Smith");
        userRequest.setPassword("qwerty");
        userRequest.setGender("MALE");
        userRequest.setPhoneNumber("+375291234567");
        userRequest.setRole("USER");

        User user = userMapper.userRequestToUser(userRequest);

        assertNotNull(user);
        assertEquals(userRequest.getId(), user.getId());
        assertEquals(userRequest.getGender(), user.getGender().toString());
        System.out.println(userRequest);
        System.out.println(user);
    }

    @Test
    void userToUserResponseTest() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("test");
        user.setEmail("test@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Smith");
        user.setPassword("qwerty");
        user.setGender(Gender.MALE);
        user.setPhoneNumber("+375291234567");
        Cart cart = new Cart();
        cart.setId(user.getId());
        cart.setTotalSum(55.0);
        cart.setReservationDayNumber(3);
        cart.setStartReservationDate(LocalDate.now());
        user.setCart(cart);
        Role role = new Role();
        role.setRoleName(RoleName.USER);
        user.setRole(role);
        cart.setUser(user);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        assertNotNull(userResponse);
        assertEquals(user.getId(), userResponse.getId());
        assertEquals(user.getGender().toString(), userResponse.getGender());
        assertEquals(user.getRole().getRoleName().toString(), userResponse.getRole());
        System.out.println(user);
        System.out.println(userResponse);
    }
}
