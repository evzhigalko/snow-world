package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.Item;

public interface BaseDaoUpdateEntity<T extends Item> extends BaseDao<T> {
    /**
     * Allow update entity by parameters
     * @param entity
     */
    T updateAvailable(T entity, boolean isAvailable);

    T updateCost(T entity, double newCost);
}
