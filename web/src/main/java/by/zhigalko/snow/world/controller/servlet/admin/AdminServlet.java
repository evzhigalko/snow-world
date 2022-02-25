package by.zhigalko.snow.world.controller.servlet.admin;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactory;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactoryImpl;
import by.zhigalko.snow.world.dao.user.UserDao;
import by.zhigalko.snow.world.dao.user.UserDaoImpl;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.Page;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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
            UserDao userDao = context.getBean("userDao", UserDaoImpl.class);
            List<User> usersList = userDao.findAllUsers();
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
            DaoEquipmentFactory<Item> daoFactory = context.getBean("daoEquipmentFactory", DaoEquipmentFactoryImpl.class);
            BaseDaoItemImpl dao = daoFactory.getDao(pageEnum);
            Item item = dao.findById(id);
            if (!cost.isEmpty()) {
                dao.updateCost(item, Double.parseDouble(cost));
            }
            if (!availableToRental.isEmpty()) {
                dao.updateAvailable(item, Boolean.parseBoolean(availableToRental));
            }
            response.sendRedirect(request.getContextPath() + pageEnum.getUrl() + page_number);
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
