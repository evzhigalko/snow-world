package by.zhigalko.snow.world.dao.item;

import by.zhigalko.snow.world.entity.Item;
import javax.persistence.Query;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Getter
@Transactional
public abstract class BaseDaoItemImpl<T extends Item> implements BaseDaoItem<T>, BaseDaoCountItem<T>, BaseDaoUpdateItem<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> iClass;

    public BaseDaoItemImpl(Class<T> iClass) {
        this.iClass = iClass;
    }

    @Override
    public boolean save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(entity) != null;
    }

    @Override
    public T findById(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("select s from %s as s where id = :id", iClass.getSimpleName()));
        query.setParameter("id", id);
        return (T) query.getSingleResult();
    }

    @Override
    public List<T> findAll(int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(String.format("select s from %s as s", iClass.getSimpleName()));
            query.setFirstResult(page);
            query.setMaxResults(pageSize);
        return (List<T>) query.getResultList();
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("delete from %s where id =:id", iClass.getSimpleName()));
        query.setParameter("id", entity.getId());
        query.executeUpdate();
    }

    @Override
    public long count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from " + iClass.getSimpleName());
        return (long) query.getSingleResult();
    }

    @Override
    public T updateAvailable(T entity, boolean isAvailable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("update %s set availableToRental = :available where id= :id", iClass.getSimpleName()));
        query.setParameter("available", isAvailable);
        query.setParameter("id", entity.getId());
        query.executeUpdate();
        return entity;
    }

    @Override
    public T updateCost(T entity, double newCost) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("update %s set cost = :value where id = :id", iClass.getSimpleName()));
        query.setParameter("value", newCost);
        query.setParameter("id", entity.getId());
        query.executeUpdate();
        return entity;
    }
}
