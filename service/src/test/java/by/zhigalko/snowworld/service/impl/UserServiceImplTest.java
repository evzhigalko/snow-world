package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.UserRequest;
import by.zhigalko.snowworld.dto.response.UserResponse;
import by.zhigalko.snowworld.entity.User;
import by.zhigalko.snowworld.mapper.UserMapper;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.RoleName;
import by.zhigalko.snowworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest("alex-s","qwerty", "test@test.com",
                "+123456789", "Alex", "Smith", String.valueOf(Gender.MALE));
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(Gender.valueOf(userRequest.getGender()));
        userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setGender(String.valueOf(user.getGender()));
    }

    @Test
    void findByUsernameIfUsernameIsNotNullTest() {
        doReturn(userResponse).when(userMapper).userToUserResponse(user);
        doReturn(user).when(userRepository).findByUsername(user.getUsername());

        UserResponse actualUserResponse = userService.findByUsername(userRequest.getUsername());

        assertThat(actualUserResponse).isEqualTo(userResponse);

        verify(userMapper).userToUserResponse(user);
        verify(userRepository).findByUsername(userRequest.getUsername());
    }

    @Test
    void createUserIfUserRequestNotNullTest() {
        doReturn(user).when(userMapper).userRequestToUser(userRequest);
        doReturn(user).when(userRepository).save(user);

        User actualUser = userService.createUser(userRequest);

        assertThat(actualUser).isEqualTo(user);

        verify(userMapper).userRequestToUser(userRequest);
        verify(userRepository).save(user);
    }

    @Test
    void findAllUsersTest() {
        List<User> userList = List.of(user);
        doReturn(userList).when(userRepository).findByRoleRoleName(RoleName.USER);
        doReturn(List.of(userResponse)).when(userMapper).userListToUserResponseList(userList);

        List<UserResponse> actualUsers = userService.findAllUsers();

        assertThat(actualUsers).isEqualTo(List.of(userResponse));

        verify(userRepository).findByRoleRoleName(RoleName.USER);
        verify(userMapper).userListToUserResponseList(userList);
    }

    @Test
    void findAllUsersShouldNotThrowAnyExceptionIfUsersNotFoundTest() {
        List<User> users = new ArrayList<>();

        doReturn(users).when(userRepository).findByRoleRoleName(RoleName.USER);
        doReturn(List.of(userResponse)).when(userMapper).userListToUserResponseList(users);

        assertThatNoException().isThrownBy(() -> userService.findAllUsers());

        verify(userRepository).findByRoleRoleName(RoleName.USER);
        verify(userMapper).userListToUserResponseList(users);
    }

    @Test
    void findByIdIfIdIsNotNullTest() {
        doReturn(user).when(userRepository).getById(user.getId());

        User actualUser = userService.findById(user.getId());

        assertThat(actualUser).isEqualTo(user);

        verify(userRepository).getById(user.getId());
    }

    @Test
    void findByIdIfIdIsNullTest() {
        doThrow(IllegalArgumentException.class).when(userRepository).getById(null);

        assertThatIllegalArgumentException().isThrownBy(() -> userService.findById(null));
    }
}
