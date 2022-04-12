package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.entity.Item;

public interface BaseDaoCountItem<T extends Item> extends BaseDaoItem<T> {
    /**
     * Count entities in data source
     * @return long
     */
    long count();
}
