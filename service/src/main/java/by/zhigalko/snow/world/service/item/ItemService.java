package by.zhigalko.snow.world.service.item;

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
import by.zhigalko.snow.world.service.common.image.ImageUploader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service("itemService")
public class ItemService {
    private final ApplicationContext context;

    @Autowired
    public ItemService(ApplicationContext context) {
        this.context=context;
    }

    public boolean saveItem(HttpServletRequest request, String product) throws ServletException, IOException {
        boolean isSaved = false;
        String maker = request.getParameter("maker");
        String hardnessLevel = request.getParameter("hardness_level");
        String ridingLevel = request.getParameter("riding_level");
        String gender = request.getParameter("gender");
        String cost = request.getParameter("cost");
        String availableToRental = request.getParameter("available_to_rental");
        String equipmentSizeId = request.getParameter("equipment_size");
        String productGroup = request.getParameter("product_group");
        String lacingSystem = request.getParameter("lacing_system");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        ImageService imageService = context.getBean("imageService", ImageService.class);
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        EquipmentSizeService equipmentSizeService = context.getBean("equipmentSizeService", EquipmentSizeService.class);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById(equipmentSizeId);
        switch (ProductGroup.valueOf(product.toUpperCase())) {
            case SNOWBOARD:
                SnowboardService snowboardService = context.getBean("snowboardService", SnowboardService.class);
                Snowboard snowboard = snowboardService.getItem(maker, HardnessLevel.valueOf(hardnessLevel), RidingLevel.valueOf(ridingLevel),
                        Gender.valueOf(gender), Double.parseDouble(cost), Boolean.parseBoolean(availableToRental),
                        equipmentSize, image, ProductGroup.valueOf(productGroup));
                image.addItem(snowboard);
                imageService.save(image);
                isSaved = snowboardService.save(snowboard);
                break;
            case SNOWBOARD_BOOT:
                SnowboardBootService snowboardBootService = context.getBean("snowboardBootService", SnowboardBootService.class);
                SnowboardBoot snowboardBoot = snowboardBootService.getItem(maker, Gender.valueOf(gender), Double.parseDouble(cost),
                        Boolean.parseBoolean(availableToRental), LacingSystem.valueOf(lacingSystem), equipmentSize,
                        image, ProductGroup.valueOf(productGroup));
                image.addItem(snowboardBoot);
                imageService.save(image);
                isSaved = snowboardBootService.save(snowboardBoot);
                break;
            case SNOWBOARD_HELMET:
                SnowboardHelmetService snowboardHelmetService = context.getBean("snowboardHelmetService", SnowboardHelmetService.class);
                SnowboardHelmet snowboardHelmet = snowboardHelmetService.getItem(maker, Gender.valueOf(gender), Double.parseDouble(cost), Boolean.parseBoolean(availableToRental),
                        equipmentSize, image, ProductGroup.valueOf(productGroup));
                image.addItem(snowboardHelmet);
                imageService.save(image);
                isSaved = snowboardHelmetService.save(snowboardHelmet);
        }
        return isSaved;
    }
}
