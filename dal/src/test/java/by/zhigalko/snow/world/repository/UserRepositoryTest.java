package by.zhigalko.snow.world.repository;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.config.ApplicationConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Rollback
@Transactional
class UserRepositoryTest {
    private User expected;
    private Role role;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        expected = new User();
        expected.setUsername("test1234");
        expected.setPassword("qwerty");
        expected.setEmail("test1234@test.com");
        role = new Role();
        role.setRoleName(RoleName.USER);
        role.addUser(expected);
        userRepository.save(expected);
    }

    @Test
    void findByUsernameAndPasswordTest() {
        User actual = userRepository.findByUsernameAndPassword(expected.getUsername(), expected.getPassword());

        assertNotNull(actual);
        assertEquals(expected.getRole().getRoleName(), actual.getRole().getRoleName());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
        System.out.println(expected);
        System.out.println(actual);
    }

    @Test
    void findByUsernameAndEmailTest() {
        User actual = userRepository.findByUsernameAndEmail(expected.getUsername(), expected.getEmail());

        assertNotNull(actual);
        assertEquals(expected.getRole().getRoleName(), actual.getRole().getRoleName());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
        System.out.println(expected);
        System.out.println(actual);
    }

    @Test
    void findAllTest() {
        List<User> userList = userRepository.findByRoleRoleName(role.getRoleName());

        userList.forEach(System.out::println);
        assertTrue(userList.size()>=1);
        assertNotNull(userList);
        assertTrue(userList.contains(expected));
    }
}
