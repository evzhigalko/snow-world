package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.UserDTO;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;

public interface AccountService {
    User createUser(UserDTO userDTO) throws ValidationException;
    boolean validate(UserDTO userDTO) throws ValidationException;
}
