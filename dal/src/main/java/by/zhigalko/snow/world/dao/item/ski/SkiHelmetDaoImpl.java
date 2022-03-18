package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("skiHelmetDao")
public class SkiHelmetDaoImpl extends BaseDaoItemImpl<SkiHelmet> {
    public SkiHelmetDaoImpl() {
        super(SkiHelmet.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<SkiHelmet> snowboardHelmetRoot = criteria.from(SkiHelmet.class);
        Join<SkiHelmet, EquipmentSize> equipmentSizeId = snowboardHelmetRoot.join("equipmentSizeId");
        criteria.select(snowboardHelmetRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
