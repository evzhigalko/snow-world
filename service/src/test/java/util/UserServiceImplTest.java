package util;

import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.service.user.UserServiceImpl;
import by.zhigalko.snow.world.util.ApplicationConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static UserServiceImpl userService;
    private static ApplicationContext context;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        userService = context.getBean("userService", UserServiceImpl.class);
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