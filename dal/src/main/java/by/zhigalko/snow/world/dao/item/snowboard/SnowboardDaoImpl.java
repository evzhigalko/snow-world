package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("snowboardDao")
public class SnowboardDaoImpl extends BaseDaoItemImpl<Snowboard> implements EquipmentAllSizesDao {
    public SnowboardDaoImpl() {
        super(Snowboard.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = SessionManager.getSession();
        List<EquipmentSize> entityList = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
            Root<EquipmentSize> equipmentSizeRoot = criteria.from(EquipmentSize.class);
            criteria.select(equipmentSizeRoot)
                    .where(cb.like(equipmentSizeRoot.get("equipmentSizeId"), "SN%"))
                    .orderBy(cb.asc(equipmentSizeRoot.get("equipmentSizeId")));
            session.getTransaction().begin();
            entityList = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        return entityList;
    }
}
