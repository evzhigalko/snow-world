package util;

import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.util.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static UserServiceImpl userService;

    @BeforeAll
    static void init() {
        userService = UserServiceImpl.getInstance();
    }

    @Test
    void validateTest() {
        //GIVEN
        String username = "test123";
        String password = "qwe213213";
        String email = "test@test.com";
        String firstName = "Alex";
        String lastName = "Smith";
        String phoneNumber = "+375291271633";
        //WHEN
        boolean isValidated;
        try {
            isValidated = userService.validate(username, password, email, firstName, lastName, phoneNumber);
        } catch (ValidationException e) {
            isValidated = false;
        }
        //THEN
        assertTrue(isValidated);
    }
}