package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("snowboardHelmetDao")
public class SnowboardHelmetDaoImpl extends BaseDaoItemImpl<SnowboardHelmet> implements EquipmentAllSizesDao {
    public SnowboardHelmetDaoImpl() {
        super(SnowboardHelmet.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<SnowboardHelmet> snowboardBootRoot = criteria.from(SnowboardHelmet.class);
        Join<SnowboardHelmet, EquipmentSize> equipmentSizeId = snowboardBootRoot.join("equipmentSizeId");
        criteria.select(snowboardBootRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
