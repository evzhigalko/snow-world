package by.zhigalko.snowworld.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FailureAuthenticationException extends Exception {
    public FailureAuthenticationException() {}

    public FailureAuthenticationException(String message) {
        super(message);
        log.error(message);
    }
}
