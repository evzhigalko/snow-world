package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.Item;

public interface BaseDaoCountEntity<T extends Item> extends BaseDao<T> {
    /**
     * Count entities in data source
     * @return long
     */
    long count();
}
