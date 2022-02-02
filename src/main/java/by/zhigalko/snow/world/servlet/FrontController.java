package by.zhigalko.snow.world.servlet;

import by.zhigalko.snow.world.dal.dao.BaseDaoCountEntity;
import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.clothes.*;
import by.zhigalko.snow.world.dal.dao.ski.SkiBootDaoImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiDaoImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiHelmetDaoImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiPoleDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardHelmetDaoImpl;
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
        BaseDaoEquipmentImpl dao = null;
        String forwardPath = null;
        try {
            Page enumClass = Page.getEnumClass(requestURI);
            dao = SnowboardDaoImpl.getInstance();
            paginate(request, page, dao);
            forwardPath = enumClass.getForwardPage();


            if (requestURI.contains(Page.SNOWBOARD_LIST.getUrl())) {
                dao = SnowboardDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.SNOWBOARD_LIST.getForwardPage();
            } else if (requestURI.contains(Page.SNOWBOARD_BOOT_LIST.getUrl())) {
                dao = SnowboardBootDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.SNOWBOARD_BOOT_LIST.getForwardPage();
            } else if(requestURI.contains(Page.SNOWBOARD_HELMET_LIST.getUrl())) {
                dao = SnowboardHelmetDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.SNOWBOARD_HELMET_LIST.getForwardPage();
            } else if(requestURI.contains(Page.SKI_LIST.getUrl())) {
                dao = SkiDaoImpl.getInstance();
                paginate(request,page, dao);
                SkiPoleDaoImpl skiPoleDao = SkiPoleDaoImpl.getInstance();
                List<SkiPole> poleList = skiPoleDao.findAll(0, 9999);
                request.setAttribute("poleList", poleList);
                forwardPath = Page.SKI_LIST.getForwardPage();
            } else if(requestURI.contains(Page.SKI_BOOT_LIST.getUrl())) {
                dao = SkiBootDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.SKI_BOOT_LIST.getForwardPage();
            } else if(requestURI.contains(Page.SKI_HELMET_LIST.getUrl())) {
                dao = SkiHelmetDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.SKI_HELMET_LIST.getForwardPage();
            } else if(requestURI.contains(Page.JACKET_LIST.getUrl())) {
                dao = JacketDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.JACKET_LIST.getForwardPage();
            } else if(requestURI.contains(Page.PANTS_LIST.getUrl())) {
                dao = PantsDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.PANTS_LIST.getForwardPage();
            } else if(requestURI.contains(Page.CAP_LIST.getUrl())) {
                dao = CapDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.CAP_LIST.getForwardPage();
            } else if(requestURI.contains(Page.MASK_LIST.getUrl())) {
                dao = MaskDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.MASK_LIST.getForwardPage();
            } else if(requestURI.contains(Page.MITTENS_LIST.getUrl())) {
                dao = MittenDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.MITTENS_LIST.getForwardPage();
            } else if(requestURI.contains(Page.GLOVES_LIST.getUrl())) {
                dao = GloveDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = Page.GLOVES_LIST.getForwardPage();
            }
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
