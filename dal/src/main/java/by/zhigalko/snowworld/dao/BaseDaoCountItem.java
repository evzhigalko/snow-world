package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.Item;

public interface BaseDaoCountItem<T extends Item> extends BaseDaoItem<T> {
    /**
     * Count entities in data source
     * @return long
     */
    long count();
}
