package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;

public class EquipmentSizeDaoImpl implements EquipmentSizeDao{
    @Override
    public EquipmentSize findById(String id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select eqs from EquipmentSize AS eqs where id = :id");
        query.setParameter("id", id);
        EquipmentSize equipmentSize = (EquipmentSize) query.getSingleResult();
        session.getTransaction().commit();
        return equipmentSize;
    }
}
