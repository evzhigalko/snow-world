package by.zhigalko.snow.world.filter;

import by.zhigalko.snow.world.entity.enums.RoleName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

@Log4j2
@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("AuthorizationFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (isPrivatePage(request)) {
            RoleName roleName = (RoleName) request.getSession().getAttribute("ROLE");
           if(roleName != null && roleName.equals(RoleName.ADMIN)) {
               chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/form/login");
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }

    private boolean isPrivatePage(HttpServletRequest req) {
        return req.getRequestURI().contains("admin");
    }
}
