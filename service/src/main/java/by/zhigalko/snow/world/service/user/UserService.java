package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User createUser(HttpServletRequest request, User user) throws ValidationException;
    User save(User user);
    User findByUsernameAndPassword(String username, String password);
    User findByUsernameAndEmail(String username, String email);
    List<User> findAllUsers();
}
