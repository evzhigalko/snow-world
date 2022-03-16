package by.zhigalko.snow.world.service.common;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import by.zhigalko.snow.world.service.common.image.ImageService;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service("itemService")
public class AdminItemService {
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final ImageService imageService;
    private final EquipmentSizeService equipmentSizeService;

    @Autowired
    public AdminItemService(ServiceEquipmentFactory serviceEquipmentFactory, ImageService imageService, EquipmentSizeService equipmentSizeService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.imageService = imageService;
        this.equipmentSizeService = equipmentSizeService;
    }

    public boolean addNewItem(HttpServletRequest request, MultipartFile filePart, String product) throws ServletException, IOException {
        boolean isSaved = false;
        String equipmentSizeId = request.getParameter("equipment_size");
        String fileName = filePart.getOriginalFilename();
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById(equipmentSizeId);
        BaseItemServiceImpl<? extends Item> service;
        switch (ProductGroup.valueOf(product.toUpperCase())) {
            case SNOWBOARD:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_LIST);
                isSaved = saveItem(request, imageService, image, equipmentSize, service);
                break;
            case SNOWBOARD_BOOT:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_BOOT_LIST);
                isSaved = saveItem(request, imageService, image, equipmentSize, service);
                break;
            case SNOWBOARD_HELMET:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_HELMET_LIST);
                isSaved = saveItem(request, imageService, image, equipmentSize, service);
        }
        return isSaved;
    }

    private boolean saveItem(HttpServletRequest request, ImageService imageService, Image image, EquipmentSize equipmentSize, BaseItemServiceImpl service) {
        boolean isSaved;
        Item item = service.getItem(request, equipmentSize, image);
        image.addItem(item);
        imageService.save(image);
        isSaved = service.save(item);
        return isSaved;
    }
}
