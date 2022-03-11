package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.Item;

public interface BaseUpdateItemService<T extends Item> extends BaseItemService<T> {
    T updateAvailable(T entity, boolean isAvailable);
    T updateCost(T entity, double newCost);
}
