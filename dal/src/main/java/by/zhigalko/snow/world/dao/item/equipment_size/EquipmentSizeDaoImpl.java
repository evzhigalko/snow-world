package by.zhigalko.snow.world.dao.item.equipment_size;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("equipmentSizeDao")
public class EquipmentSizeDaoImpl implements EquipmentSizeDao{
    @Override
    public EquipmentSize findById(String id) {
        Session session = SessionManager.getSession();
        EquipmentSize equipmentSize = null;
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("select eqs from EquipmentSize as eqs where id = :id");
            query.setParameter("id", id);
            equipmentSize = (EquipmentSize) query.getSingleResult();
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        return equipmentSize;
    }
}
