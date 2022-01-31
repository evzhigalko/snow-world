package by.zhigalko.snow.world.servlet;

import by.zhigalko.snow.world.dal.dao.BaseDaoCountEntity;
import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.dal.entity.Item;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/snowboard/catalog/*",
        "/snowboard/boot/catalog/*",
        "/snowboard/helmet/catalog/*"
})
public class CatalogServlet extends HttpServlet {
    public static final int PAGE_SIZE = 6;
    private static final String SNOWBOARD_LIST = "/WEB-INF/jsp/catalog/snowboard-list.jsp";
    private static final String SNOWBOARD_BOOT_LIST = "/WEB-INF/jsp/catalog/snowboard-boot-list.jsp";
    private static final String SNOWBOARD_HELMET_LIST = "/WEB-INF/jsp/catalog/snowboard-helmet-list.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("[^0-9]*");
        String pageStr = split[split.length - 1];
        int page = (Integer.parseInt(pageStr) - 1) * PAGE_SIZE;
        BaseDaoEquipmentImpl dao = null;
        try {
            if (requestURI.contains("/snowboard/catalog/")) {
                dao = new SnowboardDaoImpl();
                paginate(request, response, page, dao);
                getServletContext().getRequestDispatcher(SNOWBOARD_LIST).forward(request, response);
            } else if (requestURI.contains("/snowboard/boot/catalog/")) {
                dao = new SnowboardBootDaoImpl();
                paginate(request, response, page, dao);
                getServletContext().getRequestDispatcher(SNOWBOARD_BOOT_LIST).forward(request, response);
            } else if(requestURI.contains("/snowboard/helmet/catalog/")){
                dao = new SnowboardHelmetDaoImpl();
                paginate(request, response, page, dao);
                getServletContext().getRequestDispatcher(SNOWBOARD_HELMET_LIST).forward(request, response);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void paginate(HttpServletRequest request, HttpServletResponse response, int page, BaseDaoCountEntity dao) throws ServletException, IOException {
        List<Item> list = dao.findAll(page, PAGE_SIZE);
        long entityNumber = dao.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("list", list);
    }
}
