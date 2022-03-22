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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

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

    @Transactional
    public boolean addNewItem(HttpServletRequest request, MultipartFile filePart, String product) throws ServletException, IOException {
        String equipmentSizeId = request.getParameter("equipment_size");
        String fileName = filePart.getOriginalFilename();
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById(equipmentSizeId);
        return save(request, product, image, equipmentSize);
    }

    private boolean save(HttpServletRequest request, String product, Image image, EquipmentSize equipmentSize) {
        return saveItem(request, imageService, image, equipmentSize, getService(product));
    }

    @Transactional
    public void deleteItem(String product, UUID id) {
        BaseItemServiceImpl service = getService(product);
        Item item = service.findById(id);
        Image image = item.getImage();
        image.removeItem(item);
        service.delete(item);
        imageService.delete(item.getImage());
    }

    @Transactional
    public boolean updateItem(@RequestParam(value = "cost") String cost,
                              @RequestParam(value = "availability") String availableToRental,
                              @PathVariable("id") UUID id,
                              BaseItemServiceImpl service) {
        Item item = service.findById(id);
        if(!cost.isEmpty()) {
            item.setCost(Double.parseDouble(cost));
        }
        if (!availableToRental.isEmpty()) {
            item.setAvailableToRental(Boolean.parseBoolean(availableToRental));
        }
        Item savedItem = service.save(item);
        return savedItem != null;
    }

    private BaseItemServiceImpl<? extends Item> getService(String product) {
        BaseItemServiceImpl<? extends Item> service = null;
        switch (Product.valueOf(product.toUpperCase())) {
            case SNOWBOARD:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_LIST);
                break;
            case SNOWBOARD_BOOT:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_BOOT_LIST);
                break;
            case SNOWBOARD_HELMET:
                service = serviceEquipmentFactory.getService(Page.SNOWBOARD_HELMET_LIST);
                break;
            case SKI:
                service = serviceEquipmentFactory.getService(Page.SKI_LIST);
                break;
            case SKI_BOOT:
                service = serviceEquipmentFactory.getService(Page.SKI_BOOT_LIST);
                break;
            case SKI_HELMET:
                service = serviceEquipmentFactory.getService(Page.SKI_HELMET_LIST);
                break;
            case SKI_POLE:
                service = serviceEquipmentFactory.getService(Page.SKI_POLE_LIST);
                break;
            case JACKET:
                service = serviceEquipmentFactory.getService(Page.JACKET_LIST);
                break;
            case PANTS:
                service = serviceEquipmentFactory.getService(Page.PANTS_LIST);
                break;
            case GLOVE:
                service = serviceEquipmentFactory.getService(Page.GLOVES_LIST);
                break;
            case MITTEN:
                service = serviceEquipmentFactory.getService(Page.MITTENS_LIST);
                break;
            case MASK:
                service = serviceEquipmentFactory.getService(Page.MASK_LIST);
                break;
            case CAP:
                service = serviceEquipmentFactory.getService(Page.CAP_LIST);
                break;
        }
        return service;
    }

    private boolean saveItem(HttpServletRequest request, ImageService imageService, Image image, EquipmentSize equipmentSize, BaseItemServiceImpl service) {
        Item item = service.getItem(request, equipmentSize, image);
        image.addItem(item);
        imageService.save(image);
        Item savedItem = service.save(item);
        return savedItem != null;
    }
}
