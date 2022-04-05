package by.zhigalko.snow.world.service.admin;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.service.common.image.ImageService;
import by.zhigalko.snow.world.service.item.BaseItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import javax.servlet.ServletException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@Service("itemService")
public class AdminItemService {
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final ImageService imageService;

    @Autowired
    public AdminItemService(ServiceEquipmentFactory serviceEquipmentFactory, ImageService imageService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.imageService = imageService;
    }

    @Transactional
    public boolean addNewItem(ItemRequest itemRequest, MultipartFile filePart, String product) throws ServletException, IOException {
        log.fatal("ItemRequest: " + itemRequest);
        String fileName = filePart.getOriginalFilename();
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        BaseItemService service = serviceEquipmentFactory.getService(Product.valueOf(product.toUpperCase()));
        Item item = service.getItem(itemRequest, image);
        image.addItem(item);
        log.fatal("Created new item: " + item);
        imageService.save(image);
        Item savedItem = service.save(item);
        return savedItem!=null;
    }

    @Transactional
    public void deleteItem(String product, UUID id) {
        BaseItemService service = serviceEquipmentFactory.getService(Product.valueOf(product.toUpperCase()));
        Item item = service.findById(id);
        log.fatal("Deleted ---> " + item);
        Image image = item.getImage();
        log.fatal("Deleted image---> " + image);
        image.removeItem(item);
        service.delete(item);
        imageService.delete(item.getImage());
    }

    @Transactional
    public boolean updateItem(@RequestParam(value = "cost") String cost,
                              @RequestParam(value = "availability") String availableToRental,
                              @PathVariable("id") UUID id,
                              BaseItemService service) {
        Item item = service.findById(id);
        log.fatal("Found item for update: " + item);
        if(!cost.isEmpty()) {
            item.setCost(Double.parseDouble(cost));
            log.fatal("Changed cost to:  " + cost);
        }
        if (!availableToRental.isEmpty()) {
            item.setAvailableToRental(Boolean.parseBoolean(availableToRental));
            log.fatal("Changed availability to rent to:  " + availableToRental);
        }
        Item savedItem = service.save(item);
        log.fatal("Saved item after update " + savedItem);
        return savedItem != null;
    }
}
