package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.service.AdminItemService;
import by.zhigalko.snowworld.service.ImageService;
import by.zhigalko.snowworld.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class AdminItemServiceImpl implements AdminItemService {
    private final ImageService imageService;
    private final ItemService itemService;

    @Autowired
    public AdminItemServiceImpl(ImageService imageService, ItemService itemService) {
        this.imageService = imageService;
        this.itemService = itemService;
    }

    @Override
    public boolean addNewItem(ItemRequest itemRequest, MultipartFile filePart, String product) throws IOException {
        log.info("ItemRequest: " + itemRequest);
        String imageName = imageService.uploadImage(filePart, product);
        Image image = imageService.getImage(imageName);
        Item item = itemService.getItem(itemRequest, image);
        image.addItem(item);
        log.info("Created new item: " + item);
        imageService.save(image);
        Item savedItem = itemService.save(item);
        return savedItem!=null;
    }

    @Override
    public void deleteItem(UUID id) {
        Item item = itemService.findById(id);
        log.info("Deleted ---> " + item);
        Image image = item.getImage();
        log.info("Deleted image---> " + image);
        image.removeItem(item);
        itemService.delete(item);
        imageService.delete(image);
    }

    @Override
    public boolean updateItem(String cost, String availableToRental, UUID id) {
        Item item = itemService.findById(id);
        log.info("Found item for update: " + item);
        if(!cost.isEmpty()) {
            item.setCost(Double.parseDouble(cost));
            log.info("Changed cost to:  " + cost);
        }
        if (!availableToRental.isEmpty()) {
            item.setAvailableToRental(Boolean.parseBoolean(availableToRental));
            log.info("Changed availability to rent to:  " + availableToRental);
        }
        Item savedItem = itemService.save(item);
        log.info("Saved item after update " + savedItem);
        return savedItem != null;
    }
}
