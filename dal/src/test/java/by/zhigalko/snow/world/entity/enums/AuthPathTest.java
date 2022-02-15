package by.zhigalko.snow.world.entity.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthPathTest {

    @Test
    void getEnumTest() {
        //GIVEN
        String authPath = "/login";
        //WHEN
        AuthPath actual = AuthPath.getEnum(authPath);
        //THEN
        assertEquals(AuthPath.LOGIN, actual);
    }
}