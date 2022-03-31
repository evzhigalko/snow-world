package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest) throws ValidationException;
    User findByUsername(String username);
    List<User> findAllUsers();
}
