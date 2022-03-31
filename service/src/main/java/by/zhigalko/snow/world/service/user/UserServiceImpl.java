package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(UserRequest userRequest) throws ValidationException {
        User user = accountService.createUser(userRequest);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findByRoleRoleName(RoleName.USER);
    }
}
