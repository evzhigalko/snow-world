package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        Session session = getSessionFactory().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
            Root<EquipmentSize> equipmentSizeRoot = criteria.from(EquipmentSize.class);
            criteria.select(equipmentSizeRoot)
                    .where(cb.like(equipmentSizeRoot.get("equipmentSizeId"), "SN%"))
                    .orderBy(cb.asc(equipmentSizeRoot.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
