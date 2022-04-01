package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.service.user.RoleService;
import by.zhigalko.snow.world.util.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

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

        User user = userMapper.userRequestToUser(userRequest);

        assertNotNull(user);
        assertEquals(userRequest.getId(), user.getId());
        assertEquals(userRequest.getGender(), user.getGender().toString());
        assertTrue(passwordEncoder.matches(userRequest.getPassword(), user.getPassword()));
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
        role.setId(UUID.randomUUID());
        user.setRole(role);
        cart.setUser(user);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        assertNotNull(userResponse);
        assertEquals(user.getId(), userResponse.getId());
        assertEquals(user.getGender().toString(), userResponse.getGender());
        System.out.println(user);
        System.out.println(userResponse);
    }

    @Test
    void userListToUserResponseListTest() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setUsername("test");
        user1.setEmail("test@gmail.com");
        user1.setFirstName("Alex");
        user1.setLastName("Smith");
        user1.setPassword("qwerty");
        user1.setGender(Gender.MALE);
        user1.setPhoneNumber("+375291234567");
        Cart cart = new Cart();
        cart.setId(user1.getId());
        cart.setTotalSum(55.0);
        cart.setReservationDayNumber(3);
        cart.setStartReservationDate(LocalDate.now());
        user1.setCart(cart);
        Role role = new Role();
        role.setRoleName(RoleName.USER);
        role.setId(UUID.randomUUID());
        user1.setRole(role);
        cart.setUser(user1);

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setUsername("test2");
        user2.setEmail("test2@gmail.com");
        user2.setFirstName("Alex2");
        user2.setLastName("Smith2");
        user2.setPassword("qwerty2");
        user2.setGender(Gender.FEMALE);
        user2.setPhoneNumber("+111111111111");
        Cart cart2 = new Cart();
        cart2.setId(user2.getId());
        cart2.setTotalSum(77.0);
        cart2.setReservationDayNumber(11);
        cart2.setStartReservationDate(LocalDate.now());
        user2.setCart(cart2);
        Role role2 = new Role();
        role2.setRoleName(RoleName.USER);
        role2.setId(UUID.randomUUID());
        user2.setRole(role2);
        cart2.setUser(user2);

        List<User> userList = List.of(user1, user2);

        List<UserResponse> userResponseList = userMapper.userListToUserResponseList(userList);

        assertNotNull(userResponseList);
        assertEquals(userList.size(), userResponseList.size());
        assertEquals(userList.get(0).getId(), userResponseList.get(0).getId());
        assertEquals(userList.get(1).getId(), userResponseList.get(1).getId());
        assertEquals(userList.get(1).getPhoneNumber(), userResponseList.get(1).getPhoneNumber());
        userList.forEach(System.out::println);
        userResponseList.forEach(System.out::println);
    }
}
