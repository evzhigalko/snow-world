package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Pattern;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final int NAME_MIN_LENGTH = 2;
    private static final int CREDENTIALS_MIN_LENGTH = 5;
    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_NUMBER_VALIDATION_PATTERN = Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User createUser(HttpServletRequest request, User user) throws ValidationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phone_number");
        boolean isValidated = validate(username, password, email, firstName, lastName, phoneNumber);
        if (isValidated) {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            String genderName = request.getParameter("gender");
            Gender gender = Gender.valueOf(genderName);
            user.setGender(gender);
            user.setRole(roleService.findByRoleName(RoleName.USER));
        }
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findByRoleRoleName(RoleName.USER);
    }

    public boolean validate(String username, String password, String email, String firstname, String lastname, String phoneNumber) throws ValidationException {
        if (username == null || username.length() < CREDENTIALS_MIN_LENGTH
                || password == null || password.length() < CREDENTIALS_MIN_LENGTH) {
            throw new ValidationException("Имя пользователя и пароль должны быть не менее 5 символов");
        }
        if (email == null || !(EMAIL_VALIDATION_PATTERN.matcher(email).matches())) {
            throw new ValidationException("Элекронная почта не корректна");
        }
        if (firstname == null || firstname.length() < NAME_MIN_LENGTH
                || lastname == null || lastname.length() < NAME_MIN_LENGTH) {
            throw new ValidationException("Имя и фамилия должны быть не менее 2 символов");
        }
        if (phoneNumber == null || !(PHONE_NUMBER_VALIDATION_PATTERN.matcher(phoneNumber).matches())) {
            throw new ValidationException("Телефоный номер не корректен, введите телефонный номер в международном формате");
        } else {
            return true;
        }
    }
}
