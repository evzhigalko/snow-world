package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.mapper.UserMapper;
import by.zhigalko.snow.world.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountService accountService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse findByUsername(String username) {
        return userMapper.userToUserResponse(userRepository.findByUsername(username));
    }

    @Override
    public User createUser(UserRequest userRequest) throws ValidationException {
        User user = accountService.createUser(userRequest);
        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userMapper.userListToUserResponseList(userRepository.findByRoleRoleName(RoleName.USER));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(NoResultException::new);
    }
}
