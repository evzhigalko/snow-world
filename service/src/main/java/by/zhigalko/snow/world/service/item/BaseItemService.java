package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import org.springframework.data.domain.Page;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface BaseItemService<T extends Item> {
    Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image);

    T save(T item);

    Page<T> findAll(int page, int pageSize);

    T findById(UUID id);

    void delete(T item);
}
