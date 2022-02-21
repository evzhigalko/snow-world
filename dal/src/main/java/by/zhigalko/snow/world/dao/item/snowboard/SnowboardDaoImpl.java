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
import java.util.List;

public class SnowboardDaoImpl extends BaseDaoItemImpl<Snowboard> implements EquipmentAllSizesDao {
    private static volatile SnowboardDaoImpl instance = null;

    private SnowboardDaoImpl() {
        super(Snowboard.class);
    }

    public static SnowboardDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SnowboardDaoImpl.class) {
                if (instance == null) {
                    instance = new SnowboardDaoImpl();
                }
            }
        }
        return instance;
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
