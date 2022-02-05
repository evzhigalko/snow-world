package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.Item;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import java.util.List;

public abstract class BaseDaoEquipmentImpl<T extends Item> implements BaseDao<T>, BaseDaoCountEntity<T>,BaseDaoUpdateEntity<T>{
    private final Class<T> iClass;

    public BaseDaoEquipmentImpl(Class<T> iClass) {
        this.iClass = iClass;
    }
    @Override
    public void save(T entity) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
    }

    @Override
    public T findById(Long id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("select s from %s as s where id = :id", iClass.getSimpleName()));
        query.setParameter("id", id);
        T entity = (T) query.getSingleResult();
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public List<T> findAll(int page, int pageSize) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("select s from %s as s", iClass.getSimpleName()));
        query.setFirstResult(page);
        query.setMaxResults(pageSize);
        List<T> entityList = query.getResultList();
        session.getTransaction().commit();
        return entityList;
    }

    @Override
    public void update(T entity) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("delete from %s where id =:id", iClass.getSimpleName()));
        query.setParameter("id",entity.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public long count() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select count(*) from " + iClass.getSimpleName());
        long result = (long) query.getSingleResult();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public T updateAvailable(T entity, boolean isAvailable) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("update %s set availableToRental = :available where id= :id", iClass.getSimpleName()));
        query.setParameter("available", isAvailable);
        query.setParameter("id", entity.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public T updateCost(T entity, double newCost) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(String.format("update %s set cost = :value where id = :id", iClass.getSimpleName()));
        query.setParameter("value", newCost);
        query.setParameter("id", entity.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        return entity;
    }
}
