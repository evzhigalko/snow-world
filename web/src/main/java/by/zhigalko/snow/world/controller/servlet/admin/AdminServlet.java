package by.zhigalko.snow.world.controller.servlet.admin;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.item.BaseUpdateItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.user.UserService;
import by.zhigalko.snow.world.service.user.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = (ApplicationContext) getServletContext().getAttribute("context");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.info("doGet from AdminServlet");
            UserService userService = context.getBean("userService", UserServiceImpl.class);
            List<User> usersList = userService.findAllUsers();
            request.setAttribute("usersList", usersList);
            request.getRequestDispatcher(Page.ADMIN_PAGE.getForwardPage()).forward(request, response);
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost AdminServlet");
        try {
            String cost = request.getParameter("cost");
            String availableToRental = request.getParameter("availability");
            String item_id = request.getParameter("id");
            String page_number = (String) request.getSession().getAttribute("pageStr");
            UUID id = UUID.fromString(item_id);
            Page pageEnum = Page.getEnum(request.getRequestURI());
            ServiceEquipmentFactory serviceEquipmentFactory = context.getBean("serviceEquipmentFactory", ServiceEquipmentFactory.class);
            BaseUpdateItemService service = serviceEquipmentFactory.getService(pageEnum);
            Item item = service.findById(id);
            if (!cost.isEmpty()) {
                service.updateCost(item, Double.parseDouble(cost));
            }
            if (!availableToRental.isEmpty()) {
                service.updateAvailable(item, Boolean.parseBoolean(availableToRental));
            }
            response.sendRedirect(request.getContextPath() + pageEnum.getUrl() + page_number);
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
