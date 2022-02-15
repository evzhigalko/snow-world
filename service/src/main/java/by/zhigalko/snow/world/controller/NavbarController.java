package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.enums.Page;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

@Log4j2
@WebServlet({
        "/snowboard",
        "/ski",
        "/clothes",
        "/contacts",
        "/form/login",
        "/form/registration"
})
public class NavbarController extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Page navbarEnum = Page.getEnum(request.getRequestURI());
            String forwardPath = navbarEnum.getForwardPage();
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
