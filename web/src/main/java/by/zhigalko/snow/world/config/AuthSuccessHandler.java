package by.zhigalko.snow.world.config;

import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        if(RoleName.ADMIN.name().equals(role)) {
           return "/admin";
        }
        return "/welcome";
    }
}
