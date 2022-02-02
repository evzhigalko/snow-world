package by.zhigalko.snow.world.dal.dao;

import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.ski.SkiPole;
import by.zhigalko.snow.world.dal.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;

public class EquipmentSizeDaoImpl implements EquipmentSizeDao{
    private static volatile EquipmentSizeDaoImpl instance = null;

    private EquipmentSizeDaoImpl() {
    }

    public static EquipmentSizeDaoImpl getInstance() {
        if (instance == null) {
            synchronized (EquipmentSizeDaoImpl.class) {
                if (instance == null) {
                    instance = new EquipmentSizeDaoImpl();
                }
            }
        }
        return instance;
    }

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
