package by.zhigalko.snow.world.dao.item;

import by.zhigalko.snow.world.entity.Item;
import java.util.List;
import java.util.UUID;

public interface BaseDaoItem<T extends Item> {
    /**
     * Allow to process data: save, find by id, find all, update, delete entities
     * @param entity
     */
    boolean save(T entity);

    T findById(UUID id);

    List<T> findAll(int page, int pageSize);

    void update(T entity);

    void delete(T entity);
}
