package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;

public interface AccountService {
    User createUser(UserRequest userRequest) throws ValidationException;
    boolean validate(UserRequest userRequest) throws ValidationException;
}
