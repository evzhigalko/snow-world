package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.UserDTO;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private static final int NAME_MIN_LENGTH = 2;
    private static final int CREDENTIALS_MIN_LENGTH = 5;
    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_NUMBER_VALIDATION_PATTERN = Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(RoleService roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDTO userDTO) throws ValidationException {
        boolean isValidated = validate(userDTO);
        User user = null;
        if (isValidated) {
            user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setGender(userDTO.getGender());
            user.setRole(roleService.findByRoleName(RoleName.USER));
        }
        return user;
    }

    @Override
    public boolean validate(UserDTO userDTO) throws ValidationException {
        if (userDTO.getUsername() == null || userDTO.getUsername().length() < CREDENTIALS_MIN_LENGTH
                || userDTO.getPassword() == null || userDTO.getPassword().length() < CREDENTIALS_MIN_LENGTH) {
            throw new ValidationException("Имя пользователя и пароль должны быть не менее 5 символов");
        }
        if (userDTO.getEmail() == null || !(EMAIL_VALIDATION_PATTERN.matcher(userDTO.getEmail()).matches())) {
            throw new ValidationException("Элекронная почта не корректна");
        }
        if (userDTO.getFirstName() == null || userDTO.getFirstName().length() < NAME_MIN_LENGTH
                || userDTO.getLastName() == null || userDTO.getLastName().length() < NAME_MIN_LENGTH) {
            throw new ValidationException("Имя и фамилия должны быть не менее 2 символов");
        }
        if (userDTO.getPhoneNumber() == null || !(PHONE_NUMBER_VALIDATION_PATTERN.matcher(userDTO.getPhoneNumber()).matches())) {
            throw new ValidationException("Телефоный номер не корректен, введите телефонный номер в международном формате");
        } else {
            return true;
        }
    }
}
