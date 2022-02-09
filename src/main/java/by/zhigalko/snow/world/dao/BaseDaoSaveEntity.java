package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.entity.BaseEntity;

public interface BaseDaoSaveEntity<T extends BaseEntity> {
    /**
     * Save entity.
     * @param entity saving entity
     * @return <ul>
     *     <li>{@code true} if saved</li>
     *     <li>{@code false} if didn't save</li>
     * </ul>
     */
    boolean save(T entity);
}
