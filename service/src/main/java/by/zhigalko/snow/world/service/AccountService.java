package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.dto.request.UserRequest;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.exception.ValidationException;

/**
 * Service for creating account and registration user
 */
public interface AccountService {
    /**
     * Create user before saving into database
     * @param userRequest {@link UserRequest}
     * @return {@link User}
     * @throws ValidationException when user's input data don't meet the validation requirements
     */
    User createUser(UserRequest userRequest) throws ValidationException;

    /**
     * Validate user input data
     * @param userRequest {@link UserRequest}
     * @return <ul>
     * <li>{@code true} if saved</li>
     * <li>{@code false} if didn't save</li>
     * </ul>
     * @throws ValidationException when user's input data don't meet the validation requirements
     */
    boolean validate(UserRequest userRequest) throws ValidationException;
}
