package by.zhigalko.snow.world.controller.servlet;

import by.zhigalko.snow.world.dao.user.RoleDaoImpl;
import by.zhigalko.snow.world.dao.user.UserDaoImpl;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.exception.ValidationException;
import by.zhigalko.snow.world.util.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

@Log4j2
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Page.REGISTRATION_FORM.getForwardPage()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            user = userService.createUser(request);
            RoleDaoImpl roleDao = RoleDaoImpl.getInstance();
            Role role = roleDao.find(RoleName.USER);
            user.setRole(role);
            boolean userExists = userDao.findByUsernameAndEmail(user.getUsername(), user.getEmail());
            if (!userExists) {
                boolean isSaved = userDao.save(user); {
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
