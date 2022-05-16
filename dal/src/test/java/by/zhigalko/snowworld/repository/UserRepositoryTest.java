package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.entity.User;
import by.zhigalko.snowworld.model.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
    private User user;
    private Role role;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("test1234");
        user.setPassword("qwerty");
        user.setEmail("test1234@test.com");
        role = new Role();
        role.setRoleName(RoleName.USER);
        role.addUser(user);
    }

    @Test
    void findByUsernameAndPasswordTest() {
        doReturn(user).when(userRepository).findByUsernameAndPassword(user.getUsername(), user.getPassword());

        User actualUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void findByUsernameAndEmailTest() {
        doReturn(user).when(userRepository).findByUsernameAndEmail(user.getUsername(), user.getEmail());

        User actualUser = userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void findAllTest() {
        List<User> userList = List.of(user);

        doReturn(userList).when(userRepository).findByRoleRoleName(RoleName.USER);

        List<User> actualUserList = userRepository.findByRoleRoleName(role.getRoleName());

        assertThat(actualUserList).isEqualTo(userList);
    }
}
