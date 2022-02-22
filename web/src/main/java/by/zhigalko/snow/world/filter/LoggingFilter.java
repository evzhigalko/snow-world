package by.zhigalko.snow.world.filter;

import by.zhigalko.snow.world.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

@Log4j2
@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("LoggingFilter");
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            try {
                chain.doFilter(request, response);
            } finally {
                auditHttpRequest((HttpServletRequest) request, (HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void auditHttpRequest(HttpServletRequest req, HttpServletResponse resp) {
        log.info("URL: " + req.getRequestURL());
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            log.info(String.format("Username: %s", user.getUsername()));
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(String.format("Cookie: %s=%s", cookie.getName(), cookie.getValue()));
            }
        }
    }
}
