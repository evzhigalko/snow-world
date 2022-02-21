package by.zhigalko.snow.world.controller.servlet.admin;

import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactory;
import by.zhigalko.snow.world.dao.item.factory.DaoEquipmentFactoryImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.util.ImageUploader;
import io.minio.MinioClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/admin/create/new/*")
public class AdminCreateItemServlet extends HttpServlet {
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();
            String[] split = requestURI.split("/");
            String product = split[split.length - 1];
            DaoEquipmentFactory<Item> daoFactory = DaoEquipmentFactoryImpl.getInstance();
            EquipmentAllSizesDao allSizesDao = daoFactory.getAllSizesDao(product);
            List<EquipmentSize> allSizes = allSizesDao.findAllSizes();
            request.setAttribute("allSizes", allSizes);
            request.getSession().setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/create-new-item.jsp").forward(request, response);
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = (String)request.getSession().getAttribute("product");
        String image = request.getParameter("image");
        System.out.println(image);
        ImageUploader uploader = ImageUploader.getInstance();
        boolean b = uploader.uploadImage(product, "test.png", image);
        System.out.println(b);
        request.getRequestDispatcher("/admin/").forward(request, response);
    }
}
