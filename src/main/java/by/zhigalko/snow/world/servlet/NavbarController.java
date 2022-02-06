package by.zhigalko.snow.world.servlet;

import by.zhigalko.snow.world.dal.entity.enums.Navbar;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet({
        "/snowboard",
        "/ski",
        "/clothes",
        "/contacts"})
public class NavbarController extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Navbar navbarEnum = Navbar.getEnum(request.getRequestURI());
            String forwardPath = navbarEnum.getForwardPage();
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
