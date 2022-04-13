package by.zhigalko.snow.world.dao.impl;

import by.zhigalko.snow.world.dao.EquipmentSizeDao;
import by.zhigalko.snow.world.entity.EquipmentSize;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("equipmentSizeDao")
public class EquipmentSizeDaoImpl implements EquipmentSizeDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public EquipmentSizeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public EquipmentSize findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select eqs from EquipmentSize as eqs where id = :id");
        query.setParameter("id", id);
        return (EquipmentSize) query.getSingleResult();
    }
}