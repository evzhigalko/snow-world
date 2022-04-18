package by.zhigalko.snowworld.config;

import by.zhigalko.snowworld.exception.FailureAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        try {
            throw new FailureAuthenticationException();
        } catch (FailureAuthenticationException e) {
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }
}
