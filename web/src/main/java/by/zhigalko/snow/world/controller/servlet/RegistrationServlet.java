package by.zhigalko.snow.world.controller.servlet;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.service.user.RoleService;
import by.zhigalko.snow.world.service.user.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@Log4j2
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = (ApplicationContext) getServletContext().getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Page.REGISTRATION_FORM.getForwardPage()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);
        try {
            user = userService.createUser(request);
            RoleService roleService = context.getBean("roleService", RoleService.class);
            Role role = roleService.findByRoleName(RoleName.USER);
            user.setRole(role);
            boolean userExists = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
            if (!userExists) {
                boolean isSaved = userService.save(user); {
                    if(isSaved) {
                        request.getRequestDispatcher(Page.LOGIN_FORM.getForwardPage()).forward(request, response);
                    }
                }
            } else {
                request.setAttribute("error", "Пользователь с таким именем или электронной почтой уже существует");
                request.getRequestDispatcher(Page.REGISTRATION_FORM.getForwardPage()).forward(request, response);
            }
        } catch (ValidationException e) {
            log.info(e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(Page.REGISTRATION_FORM.getForwardPage()).forward(request, response);
        }
    }
}
