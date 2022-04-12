package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.service.AdminItemService;
import by.zhigalko.snow.world.service.ImageService;
import by.zhigalko.snow.world.service.BaseItemService;
import by.zhigalko.snow.world.service.util.ServiceEquipmentFactory;
import javax.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class AdminItemServiceImpl implements AdminItemService {
    private final ServiceEquipmentFactory serviceEquipmentFactory;
    private final ImageService imageService;

    @Autowired
    public AdminItemServiceImpl(ServiceEquipmentFactory serviceEquipmentFactory, ImageService imageService) {
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.imageService = imageService;
    }

    @Transactional
    @Override
    public boolean addNewItem(ItemRequest itemRequest, MultipartFile filePart, String product) throws ServletException, IOException {
        log.info("ItemRequest: " + itemRequest);
        String fileName = filePart.getOriginalFilename();
        String imageName = imageService.uploadImage(filePart, product, fileName);
        Image image = imageService.getImage(imageName);
        BaseItemService service = serviceEquipmentFactory.getService(Product.valueOf(product.toUpperCase()));
        Item item = service.getItem(itemRequest, image);
        image.addItem(item);
        log.info("Created new item: " + item);
        imageService.save(image);
        Item savedItem = service.save(item);
        return savedItem!=null;
    }

    @Transactional
    @Override
    public void deleteItem(String product, UUID id) {
        BaseItemService service = serviceEquipmentFactory.getService(Product.valueOf(product.toUpperCase()));
        Item item = service.findById(id);
        log.info("Deleted ---> " + item);
        Image image = item.getImage();
        log.info("Deleted image---> " + image);
        image.removeItem(item);
        service.delete(item);
        imageService.delete(item.getImage());
    }

    @Transactional
    @Override
    public boolean updateItem(String cost, String availableToRental, UUID id, BaseItemService service) {
        Item item = service.findById(id);
        log.info("Found item for update: " + item);
        if(!cost.isEmpty()) {
            item.setCost(Double.parseDouble(cost));
            log.info("Changed cost to:  " + cost);
        }
        if (!availableToRental.isEmpty()) {
            item.setAvailableToRental(Boolean.parseBoolean(availableToRental));
            log.info("Changed availability to rent to:  " + availableToRental);
        }
        Item savedItem = service.save(item);
        log.info("Saved item after update " + savedItem);
        return savedItem != null;
    }
}
