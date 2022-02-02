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
public class CatalogServlet extends HttpServlet {
    public static final int PAGE_SIZE = 6;
    private static final String SNOWBOARD_LIST = "/WEB-INF/jsp/catalog/snowboard-list.jsp";
    private static final String SNOWBOARD_BOOT_LIST = "/WEB-INF/jsp/catalog/snowboard-boot-list.jsp";
    private static final String SNOWBOARD_HELMET_LIST = "/WEB-INF/jsp/catalog/snowboard-helmet-list.jsp";
    private static final String SKI_LIST = "/WEB-INF/jsp/catalog/ski-list.jsp";
    private static final String SKI_BOOT_LIST = "/WEB-INF/jsp/catalog/ski-boot-list.jsp";
    private static final String SKI_HELMET_LIST = "/WEB-INF/jsp/catalog/ski-helmet-list.jsp";
    private static final String JACKET_LIST = "/WEB-INF/jsp/catalog/jacket-list.jsp";
    private static final String PANTS_LIST = "/WEB-INF/jsp/catalog/pants-list.jsp";
    private static final String CAP_LIST = "/WEB-INF/jsp/catalog/cap-list.jsp";
    private static final String MASK_LIST = "/WEB-INF/jsp/catalog/mask-list.jsp";
    private static final String MITTENS_LIST = "/WEB-INF/jsp/catalog/mittens-list.jsp";
    private static final String GLOVES_LIST = "/WEB-INF/jsp/catalog/gloves-list.jsp";
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
            if (requestURI.contains("/snowboard/catalog/")) {
                dao = SnowboardDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = SNOWBOARD_LIST;
            } else if (requestURI.contains("/snowboard/boot/catalog/")) {
                dao = SnowboardBootDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = SNOWBOARD_BOOT_LIST;
            } else if(requestURI.contains("/snowboard/helmet/catalog/")){
                dao = SnowboardHelmetDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = SNOWBOARD_HELMET_LIST;
            } else if(requestURI.contains("/ski/catalog/")) {
                dao = SkiDaoImpl.getInstance();
                paginate(request,page, dao);
                SkiPoleDaoImpl skiPoleDao = SkiPoleDaoImpl.getInstance();
                List<SkiPole> poleList = skiPoleDao.findAll(0, 9999);
                request.setAttribute("poleList", poleList);
                forwardPath = SKI_LIST;
            } else if(requestURI.contains("/ski/boot/catalog/")) {
                dao = SkiBootDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = SKI_BOOT_LIST;
            } else if(requestURI.contains("/ski/helmet/catalog/")) {
                dao = SkiHelmetDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = SKI_HELMET_LIST;
            } else if(requestURI.contains("/clothes/jacket/catalog/")) {
                dao = JacketDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = JACKET_LIST;
            } else if(requestURI.contains("/clothes/pants/catalog/")) {
                dao = PantsDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = PANTS_LIST;
            } else if(requestURI.contains("/clothes/cap/catalog/")) {
                dao = CapDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = CAP_LIST;
            } else if(requestURI.contains("/clothes/mask/catalog/")) {
                dao = MaskDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = MASK_LIST;
            } else if(requestURI.contains("/clothes/mittens/catalog/")) {
                dao = MittenDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = MITTENS_LIST;
            } else if(requestURI.contains("/clothes/gloves/catalog/")) {
                dao = GloveDaoImpl.getInstance();
                paginate(request, page, dao);
                forwardPath = GLOVES_LIST;
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
