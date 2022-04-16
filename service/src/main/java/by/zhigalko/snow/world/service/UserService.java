package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.dto.request.UserRequest;
import by.zhigalko.snow.world.dto.response.UserResponse;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.model.RoleName;
import java.util.List;
import java.util.UUID;

/**
 * Service for user
 */
public interface UserService {
    /**
     * Create user before saving into database
     * @param userRequest {@link UserRequest} dto from {@link User}
     * @return {@link User}
     */
    User createUser(UserRequest userRequest);

    /**
     * Find user by username
     * @param username {@link String} is received from {@link org.springframework.security.core.Authentication}
     * @return {@link UserResponse}
     */
    UserResponse findByUsername(String username);

    /**
     * Find users with {@link RoleName} equals User
     * @return {@link List<UserResponse>}
     */
    List<UserResponse> findAllUsers();

    /**
     * Find user by
     * @param id {@link UUID}
     * @return {@link User}
     */
    User findById(UUID id);
}
