package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("snowboardBootDao")
public class SnowboardBootDaoImpl extends BaseDaoItemImpl<SnowboardBoot> {
    public SnowboardBootDaoImpl() {
        super(SnowboardBoot.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<SnowboardBoot> snowboardBootRoot = criteria.from(SnowboardBoot.class);
        Join<SnowboardBoot, EquipmentSize> equipmentSizeId = snowboardBootRoot.join("equipmentSizeId");
        criteria.select(snowboardBootRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
