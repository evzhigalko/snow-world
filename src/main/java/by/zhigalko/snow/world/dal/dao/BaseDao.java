package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.Item;

import java.util.List;
import java.util.UUID;

public interface BaseDao <T extends Item> {
    /**
     * Allow to process data: save, find by id, find all, update, delete entities
     * @param entity
     */
    void save(T entity);

    T findById(UUID id);

    List<T> findAll(int page, int pageSize);

    void update(T entity);

    void delete(T entity);
}
