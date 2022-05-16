package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.UserRequest;
import by.zhigalko.snowworld.dto.response.UserResponse;
import by.zhigalko.snowworld.entity.User;
import by.zhigalko.snowworld.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    private UserRequest userRequest;
    private UserResponse userResponse;
    private User user;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setId(UUID.randomUUID());
        userRequest.setUsername("test");
        userRequest.setEmail("test@gmail.com");
        userRequest.setFirstName("Alex");
        userRequest.setLastName("Smith");
        userRequest.setPassword("qwerty");
        userRequest.setGender("MALE");
        userRequest.setPhoneNumber("+375291234567");
        user = new User();
        user.setId(userRequest.getId());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setGender(Gender.valueOf(userRequest.getGender()));
        user.setPhoneNumber(userRequest.getPhoneNumber());
        userResponse= new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setGender(String.valueOf(user.getGender()));
        userResponse.setPhoneNumber(user.getPhoneNumber());
    }

    @Test
    void userRequestToUserTest() {
        doReturn(user).when(userMapper).userRequestToUser(userRequest);

        User actualUser = userMapper.userRequestToUser(userRequest);

        assertThat(actualUser).isEqualTo(user);
        verify(userMapper).userRequestToUser(userRequest);
    }

    @Test
    void userToUserResponseTest() {
        doReturn(userResponse).when(userMapper).userToUserResponse(user);

        UserResponse actualUserResponse = userMapper.userToUserResponse(user);

        assertThat(actualUserResponse).isEqualTo(userResponse);
        verify(userMapper).userToUserResponse(user);
    }

    @Test
    void userListToUserResponseListTest() {
        List<User> userList = List.of(user);
        doReturn(List.of(userResponse)).when(userMapper).userListToUserResponseList(userList);

        List<UserResponse> userResponseList = userMapper.userListToUserResponseList(userList);

        assertThat(userResponseList).isEqualTo(List.of(userResponse));
        verify(userMapper).userListToUserResponseList(userList);
    }
}
