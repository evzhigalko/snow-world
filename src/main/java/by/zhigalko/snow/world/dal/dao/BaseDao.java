package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.Item;

import java.util.List;

public interface BaseDao <T extends Item> {
    /**
     * Allow to process data: save, find by id, find all, update, delete entities
     * @param entity
     */
    void save(T entity);

    T findById(Long id);

    List<T> findAll(int page, int pageSize);

    void update(T entity);

    void delete(T entity);
}
