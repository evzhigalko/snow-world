package by.zhigalko.snow.world.util;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    User createUser(HttpServletRequest request) throws ValidationException;
}
