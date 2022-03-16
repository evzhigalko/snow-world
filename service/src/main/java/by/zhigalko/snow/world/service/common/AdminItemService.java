package by.zhigalko.snow.world.service.common;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import by.zhigalko.snow.world.service.common.image.ImageService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardBootService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardHelmetService;
import by.zhigalko.snow.world.service.item.snowboard.SnowboardService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service("itemService")
public class AdminItemService {
    private final ApplicationContext context;

    @Autowired
    public AdminItemService(ApplicationContext context) {
        this.context=context;
    }

    public boolean saveItem(HttpServletRequest request, MultipartFile filePart, String product) throws ServletException, IOException {
        boolean isSaved = false;
        String equipmentSizeId = request.getParameter("equipment_size");
        String fileName = filePart.getOriginalFilename();
        ImageService imageService = context.getBean("imageService", ImageService.class);
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        EquipmentSizeService equipmentSizeService = context.getBean("equipmentSizeService", EquipmentSizeService.class);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById(equipmentSizeId);
        switch (ProductGroup.valueOf(product.toUpperCase())) {
            case SNOWBOARD:
                SnowboardService snowboardService = context.getBean("snowboardService", SnowboardService.class);
                Snowboard snowboard = snowboardService.getItem(request, equipmentSize, image);
                image.addItem(snowboard);
                imageService.save(image);
                isSaved = snowboardService.save(snowboard);
                break;
            case SNOWBOARD_BOOT:
                SnowboardBootService snowboardBootService = context.getBean("snowboardBootService", SnowboardBootService.class);
                SnowboardBoot snowboardBoot = snowboardBootService.getItem(request, equipmentSize, image);
                image.addItem(snowboardBoot);
                imageService.save(image);
                isSaved = snowboardBootService.save(snowboardBoot);
                break;
            case SNOWBOARD_HELMET:
                SnowboardHelmetService snowboardHelmetService = context.getBean("snowboardHelmetService", SnowboardHelmetService.class);
                SnowboardHelmet snowboardHelmet = snowboardHelmetService.getItem(request, equipmentSize, image);
                image.addItem(snowboardHelmet);
                imageService.save(image);
                isSaved = snowboardHelmetService.save(snowboardHelmet);
        }
        return isSaved;
    }
}
