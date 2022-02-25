package by.zhigalko.snow.world.dao.item.equipment_size;

import by.zhigalko.snow.world.entity.EquipmentSize;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("equipmentSizeDao")
public class EquipmentSizeDaoImpl implements EquipmentSizeDao{
    private SessionFactory sessionFactory;

    @Autowired
    public EquipmentSizeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public EquipmentSize findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        EquipmentSize equipmentSize = null;
        Query query = session.createQuery("select eqs from EquipmentSize as eqs where id = :id");
        query.setParameter("id", id);
        equipmentSize = (EquipmentSize) query.getSingleResult();
        return equipmentSize;
    }
}
