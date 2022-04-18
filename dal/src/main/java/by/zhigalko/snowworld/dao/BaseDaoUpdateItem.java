package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.Item;

public interface BaseDaoUpdateItem<T extends Item> extends BaseDaoItem<T> {
    /**
     * Allow update entity by parameters
     * @param entity
     */
    T updateAvailable(T entity, boolean isAvailable);

    T updateCost(T entity, double newCost);
}
