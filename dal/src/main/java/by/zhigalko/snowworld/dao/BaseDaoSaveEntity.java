package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.BaseEntity;

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
