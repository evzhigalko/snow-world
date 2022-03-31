package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private static final int NAME_MIN_LENGTH = 2;
    private static final int CREDENTIALS_MIN_LENGTH = 5;
    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_NUMBER_VALIDATION_PATTERN = Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Autowired
    public AccountServiceImpl(RoleService roleService, UserMapper userMapper) {
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(UserRequest userRequest) throws ValidationException {
        boolean isValidated = validate(userRequest);
        User user = null;
        if (isValidated) {
            user = userMapper.userRequestToUser(userRequest);
        }
        return user;
    }

    @Override
    public boolean validate(UserRequest userRequest) throws ValidationException {
        if (userRequest.getUsername() == null || userRequest.getUsername().length() < CREDENTIALS_MIN_LENGTH
                || userRequest.getPassword() == null || userRequest.getPassword().length() < CREDENTIALS_MIN_LENGTH) {
            throw new ValidationException("Имя пользователя и пароль должны быть не менее 5 символов");
        }
        if (userRequest.getEmail() == null || !(EMAIL_VALIDATION_PATTERN.matcher(userRequest.getEmail()).matches())) {
            throw new ValidationException("Элекронная почта не корректна");
        }
        if (userRequest.getFirstName() == null || userRequest.getFirstName().length() < NAME_MIN_LENGTH
                || userRequest.getLastName() == null || userRequest.getLastName().length() < NAME_MIN_LENGTH) {
            throw new ValidationException("Имя и фамилия должны быть не менее 2 символов");
        }
        if (userRequest.getPhoneNumber() == null || !(PHONE_NUMBER_VALIDATION_PATTERN.matcher(userRequest.getPhoneNumber()).matches())) {
            throw new ValidationException("Телефоный номер не корректен, введите телефонный номер в международном формате");
        } else {
            return true;
        }
    }
}
