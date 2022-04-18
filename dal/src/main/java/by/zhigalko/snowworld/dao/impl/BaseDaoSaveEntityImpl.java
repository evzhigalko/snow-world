package by.zhigalko.snowworld.dao.impl;

import by.zhigalko.snowworld.dao.BaseDaoSaveEntity;
import by.zhigalko.snowworld.entity.BaseEntity;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Transactional
public abstract class BaseDaoSaveEntityImpl<T extends BaseEntity> implements BaseDaoSaveEntity<T> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return true;
    }
}
