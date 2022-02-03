package by.zhigalko.snow.world.servlet;

import by.zhigalko.snow.world.dal.dao.BaseDaoCountEntity;
import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.factory.DaoEquipmentFactory;
import by.zhigalko.snow.world.dal.dao.factory.DaoEquipmentFactoryImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiPoleDaoImpl;
import by.zhigalko.snow.world.dal.entity.Item;
import by.zhigalko.snow.world.dal.entity.enums.Page;
import by.zhigalko.snow.world.dal.entity.ski.SkiPole;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/snowboard/catalog/*",
        "/snowboard/boot/catalog/*",
        "/snowboard/helmet/catalog/*",
        "/ski/catalog/*",
        "/ski/boot/catalog/*",
        "/ski/helmet/catalog/*",
        "/clothes/jacket/catalog/*",
        "/clothes/pants/catalog/*",
        "/clothes/cap/catalog/*",
        "/clothes/mask/catalog/*",
        "/clothes/mittens/catalog/*",
        "/clothes/gloves/catalog/*"
})
public class FrontController extends HttpServlet {
    public static final int PAGE_SIZE = 6;
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("[^0-9]*");
        String pageStr = split[split.length - 1];
        int page = (Integer.parseInt(pageStr) - 1) * PAGE_SIZE;
        try {
            Page pageEnum = Page.getEnum(requestURI);
            DaoEquipmentFactory<Item> daoFactory = new DaoEquipmentFactoryImpl();
            BaseDaoEquipmentImpl dao = daoFactory.getDao(pageEnum);
            paginate(request, page, dao);
            if (pageEnum.equals(Page.SKI_LIST)) {
                SkiPoleDaoImpl skiPoleDao = SkiPoleDaoImpl.getInstance();
                List<SkiPole> poleList = skiPoleDao.findAll(0, 9999);
                request.setAttribute("poleList", poleList);
            }
            String forwardPath = pageEnum.getForwardPage();
            getServletContext().getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void paginate(HttpServletRequest request, int page, BaseDaoCountEntity dao) throws ServletException, IOException {
        List<Item> list = dao.findAll(page, PAGE_SIZE);
        long entityNumber = dao.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("list", list);
    }
}
