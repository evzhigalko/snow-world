package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.UserRequest;
import by.zhigalko.snow.world.dto.response.UserResponse;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.model.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.mapper.UserMapper;
import by.zhigalko.snow.world.repository.UserRepository;
import by.zhigalko.snow.world.service.AccountService;
import by.zhigalko.snow.world.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
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
