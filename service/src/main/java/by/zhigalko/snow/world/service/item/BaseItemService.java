package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public interface BaseItemService<T extends Item> {
    Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image);

    boolean save(T item);

    List<T> findAll(int page, int pageSize);

    long count();

    T findById(UUID id);
}
