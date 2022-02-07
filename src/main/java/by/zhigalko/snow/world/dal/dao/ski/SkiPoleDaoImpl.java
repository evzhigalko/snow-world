package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.ski.SkiPole;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import java.util.List;

public class SkiPoleDaoImpl extends BaseDaoEquipmentImpl<SkiPole> {
    private static volatile SkiPoleDaoImpl instance = null;

    private SkiPoleDaoImpl() {
        super(SkiPole.class);
    }

    public static SkiPoleDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SkiPoleDaoImpl.class) {
                if (instance == null) {
                    instance = new SkiPoleDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<EquipmentSize> findAllSizes() {
        Session session = SessionManager.getSession();
        List<EquipmentSize> entityList = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
            Root<SkiPole> skiPole = criteria.from(SkiPole.class);
            Join<SkiPole, EquipmentSize> equipmentSizeId = skiPole.join("equipmentSizeId");
            criteria.select(skiPole.get("equipmentSizeId"))
                    .groupBy(skiPole.get("equipmentSizeId"), equipmentSizeId.get("userMinHeight"))
                    .orderBy(cb.asc(equipmentSizeId.get("userMinHeight")));
            session.getTransaction().begin();
            entityList = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        return entityList;
    }
}
