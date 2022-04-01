package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;
import java.util.List;

/**
 * Service for user
 */
public interface UserService {
    /**
     * Create user before saving into database
     * @param userRequest {@link UserRequest} dto from {@link User}
     * @return {@link User}
     * @throws ValidationException {@link ValidationException} throws when user's input parameters don't meet the requirements
     */
    User createUser(UserRequest userRequest) throws ValidationException;

    /**
     * Find user by username
     * @param username {@link String} is received from {@link org.springframework.security.core.Authentication}
     * @return {@link UserResponse}
     */
    UserResponse findByUsername(String username);

    /**
     * Find users with {@link by.zhigalko.snow.world.entity.enums.RoleName} equals User
     * @return {@link List<UserResponse>}
     */
    List<UserResponse> findAllUsers();
}
