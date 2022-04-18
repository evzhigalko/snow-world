package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.ProductGroup;
import by.zhigalko.snowworld.service.impl.ImageServiceImpl;

import java.util.List;
import java.util.UUID;

/**
 * Service for base item
 * @param <T>
 */
public interface BaseItemService<T extends Item> {
    /**
     * Get {@link Item} by
     * @param itemRequest {@link ItemRequest} is received from presentation layer.
     * @param image {@link Image} is created in {@link ImageServiceImpl}
     * @return {@link Item}
     */
    Item getItem(ItemRequest itemRequest, Image image);

    /**
     * Save entity
     * @param item entity
     * @return saved entity
     */
    T save(T item);

    /**
     * Get list of items
     * @param page number of page, is used for pagination.
     * @param pageSize items quantity in ine page, is used for pagination.
     * @return {@link List} of items
     */
    List<? extends ItemResponse> findAll(int page, int pageSize);

    /**
     * Find entity by
     * @param id {@link UUID}
     * @return found entity
     */
    T findById(UUID id);

    /**
     * Delete entity from database
     * @param item entity
     */
    void delete(T item);

    /**
     * Find list of entities by
     * @param productGroup {@link ProductGroup}
     * @return {@link List< EquipmentSize >}
     */
    List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup);
}
