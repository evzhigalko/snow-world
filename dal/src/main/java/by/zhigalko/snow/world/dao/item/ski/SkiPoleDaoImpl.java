package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.entity.BaseEntity;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.SkiPole;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("skiPoleDao")
public class SkiPoleDaoImpl extends BaseDaoItemImpl<SkiPole> implements EquipmentAllSizesDao {
    public SkiPoleDaoImpl() {
        super(SkiPole.class);
    }

    @Override
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
