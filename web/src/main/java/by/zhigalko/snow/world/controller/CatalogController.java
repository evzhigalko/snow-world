package by.zhigalko.snow.world.controller;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import by.zhigalko.snow.world.service.item.BaseItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import by.zhigalko.snow.world.service.item.ski.SkiPoleService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
        context = (ApplicationContext) getServletContext().getAttribute("context");
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
            ServiceEquipmentFactory serviceEquipmentFactory = context.getBean("serviceEquipmentFactory", ServiceEquipmentFactory.class);
            BaseItemService<? extends Item> service = serviceEquipmentFactory.getService(pageEnum);
            paginate(request, page, service);
            if (pageEnum.equals(Page.SKI_LIST)) {
                SkiPoleService skiPoleService = context.getBean("skiPoleService", SkiPoleService.class);
                List<EquipmentSize> skiPoleSizeList = skiPoleService.findAllSizes();
                request.setAttribute("skiPoleSizeList", skiPoleSizeList);
            }
            String forwardPath = pageEnum.getForwardPage();
            getServletContext().getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            log.info(e.getMessage());
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void paginate(HttpServletRequest request, int page, BaseItemService<? extends Item> service) throws ServletException, IOException {
        List<? extends Item> list = service.findAll(page, PAGE_SIZE);
        long entityNumber = service.count();
        long pagesNumber = (long) Math.ceil(entityNumber * 1.0 / PAGE_SIZE);
        request.setAttribute("pagesNumber", pagesNumber);
        request.setAttribute("list", list);
    }
}
