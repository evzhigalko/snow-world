package by.zhigalko.snow.world.dao.item;

import by.zhigalko.snow.world.entity.Item;

public interface BaseDaoUpdateItem<T extends Item> extends BaseDaoItem<T> {
    /**
     * Allow update entity by parameters
     * @param entity
     */
    T updateAvailable(T entity, boolean isAvailable);

    T updateCost(T entity, double newCost);
}
