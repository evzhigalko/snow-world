package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactory;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactoryImpl;
import by.zhigalko.snow.world.dao.item.ski.SkiPoleDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.util.List;

@Log4j2
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
public class CatalogController extends HttpServlet {
    public static final int PAGE_SIZE = 6;
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        this.context = (ApplicationContext) getServletContext().getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("[^0-9]*");
        String pageStr = split[split.length - 1];
        request.getSession().setAttribute("pageStr", pageStr);
        int page = (Integer.parseInt(pageStr) - 1) * PAGE_SIZE;
        try {
            Page pageEnum = Page.getEnum(requestURI);
            DaoEquipmentFactory<Item> daoFactory = context.getBean("daoEquipmentFactory", DaoEquipmentFactoryImpl.class);
            BaseDaoItemImpl dao = daoFactory.getDao(pageEnum);
            paginate(request, page, dao);
            if (pageEnum.equals(Page.SKI_LIST)) {
                SkiPoleDaoImpl skiPoleDao = context.getBean("skiPoleDao", SkiPoleDaoImpl.class);
                List<EquipmentSize> skiPoleSizeList = skiPoleDao.findAllSizes();
                request.setAttribute("skiPoleSizeList", skiPoleSizeList);
            }
            String forwardPath = pageEnum.getForwardPage();
            getServletContext().getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            log.info(e.getMessage());
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void paginate(HttpServletRequest request, int page, BaseDaoItemImpl dao) throws ServletException, IOException {
        List<Item> list = dao.findAll(page, PAGE_SIZE);
        long entityNumber = dao.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("list", list);
    }
}
