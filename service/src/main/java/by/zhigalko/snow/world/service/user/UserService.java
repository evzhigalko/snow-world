package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    User createUser(HttpServletRequest request) throws ValidationException;
    boolean save(User user);
    User findByUsernameAndPassword(String username, String password);
    boolean findByUsernameAndEmail(String username, String email);
    List<User> findAllUsers();
}
