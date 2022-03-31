package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest) throws ValidationException;
    User save(User user);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    User findByUsernameAndEmail(String username, String email);
    List<User> findAllUsers();
}
