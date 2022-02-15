package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.entity.BaseEntity;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;

public abstract class BaseDaoSaveEntityImpl<T extends BaseEntity> implements BaseDaoSaveEntity<T>{
    @Override
    public boolean save(T entity) {
        Session session = SessionManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }
}
