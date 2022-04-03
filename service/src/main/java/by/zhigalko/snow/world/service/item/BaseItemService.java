package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import java.util.List;
import java.util.UUID;

public interface BaseItemService<T extends Item> {
    Item getItem(ItemRequest itemRequest, Image image);

    T save(T item);

    List<? extends ItemResponse> findAll(int page, int pageSize);

    T findById(UUID id);

    void delete(T item);

    List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup);
}
