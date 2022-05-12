package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.mapper.ItemMapper;
import by.zhigalko.snowworld.model.*;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.service.ItemService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@Getter
public class ItemServiceImpl implements ItemService {
    public static int totalPages;
    private final ItemRepository itemRepository;
    private final EquipmentSizeRepository equipmentSizeRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, EquipmentSizeRepository equipmentSizeRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.equipmentSizeRepository = equipmentSizeRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Item item = itemMapper.itemRequestToItem(itemRequest);
        item.setImage(image);
        Map<String, Object> itemInformation = item.getItemInformation();
        if (itemRequest.getHardnessLevel() != null) {
            itemInformation.put("hardnessLevel", HardnessLevel.valueOf(itemRequest.getHardnessLevel()).getName());
        }
        if (itemRequest.getLacingSystem() != null) {
            itemInformation.put("lacingSystem", LacingSystem.valueOf(itemRequest.getLacingSystem()).getMechanism());
        }
        if (itemRequest.getRidingLevel() != null) {
            itemInformation.put("ridingLevel", RidingLevel.valueOf(itemRequest.getRidingLevel()).getName());
        }
        if (itemRequest.getMembrane() != null) {
            itemInformation.put("membrane", itemRequest.getMembrane());
        }
        log.info("Got item from ItemRequest: " + item);
        return item;
    }

    @Override
    public Item save(Item item) {
        Item saved = itemRepository.save(item);
        log.info("Saved entity: " + saved);
        return saved;
    }

    @Override
    public Set<ItemResponse> findAll(Product product, int page, int pageSize) {
        Page<Item> itemPage = itemRepository.findAllByProductName(product, PageRequest.of(page, pageSize));
        totalPages = itemPage.getTotalPages();
        log.info("Found pages: " + totalPages);
        Set<Item> itemSet = itemPage.stream().collect(Collectors.toSet());
        return itemMapper.itemSetToItemResponseSet(itemSet);
    }

    @Transactional
    @Override
    public Item findById(UUID id) {
        Item item = itemRepository.getById(id);
        log.info("Found entity " + item);
        return item;
    }

    @Override
    public void delete(Item item) {
        log.info("Deleted entity " + item);
        itemRepository.deleteById(item.getId());
    }

    @Override
    public List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup) {
        List<EquipmentSize> sizeList = equipmentSizeRepository.findAllByProductGroupOrderByEquipmentSizeId(productGroup);
        log.info("Got " + sizeList);
        return sizeList;
    }
}
