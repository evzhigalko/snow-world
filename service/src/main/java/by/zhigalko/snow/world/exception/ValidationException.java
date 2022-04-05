package by.zhigalko.snow.world.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
        log.error(message);
    }
}
