package by.zhigalko.snow.world.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet({
        "/snowboard",
        "/ski",
        "/clothes"})
public class NavbarControllerServlet extends HttpServlet {
    public static final String SNOWBOARD_GROUP = "/WEB-INF/jsp/snowboard-group.jsp";
    public static final String SKI_GROUP = "/WEB-INF/jsp/ski-group.jsp";
    public static final String CLOTHES_GROUP = "/WEB-INF/jsp/clothes-group.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();
            String forwardPath = null;
            if (requestURI.contains("/snowboard")) {
                forwardPath = SNOWBOARD_GROUP;
            } else if (requestURI.contains("/ski")) {
                forwardPath = SKI_GROUP;
            } else if (requestURI.contains("/clothes")){
                forwardPath = CLOTHES_GROUP;
            }
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
