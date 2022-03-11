package by.zhigalko.snow.world.filter;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.AuthPath;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.service.user.UserService;
import by.zhigalko.snow.world.service.user.UserServiceImpl;
import jakarta.persistence.NoResultException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@Log4j2
@WebFilter({
        "/login",
        "/logout"
})
public class AuthentificationFilter implements Filter {
    private ApplicationContext context;
    private static final String MAIN_PAGE = "/index.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        context = (ApplicationContext) filterConfig.getServletContext().getAttribute("context");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        log.info("AuthentificationFilter");
        switch (AuthPath.getEnum(request.getRequestURI())) {
            case LOGIN:
                login(request, response);
                break;
            case LOGOUT:
                logout(request, response);
                break;
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            UserService userService = context.getBean("userService", UserServiceImpl.class);
            User user = userService.findByUsernameAndPassword(req.getParameter("username"), req.getParameter("password"));
            req.getSession().isNew();
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("ROLE", user.getRole().getRoleName());
            if (user.getRole().getRoleName().equals(RoleName.ADMIN)) {
                resp.sendRedirect(req.getContextPath() + Page.ADMIN_PAGE.getUrl());
            } else {
                resp.sendRedirect(req.getContextPath() + MAIN_PAGE);
            }
        } catch (NoResultException e) {
            log.info(e.getMessage());
            req.getSession().invalidate();
            req.setAttribute("error", "Имя пользователя или пароль не корректны");
            req.getRequestDispatcher(Page.LOGIN_FORM.getForwardPage()).forward(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher(Page.LOGIN_FORM.getForwardPage()).forward(req, resp);
    }
}
