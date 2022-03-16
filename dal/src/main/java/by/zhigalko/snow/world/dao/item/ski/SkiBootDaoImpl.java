package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("skiBootDao")
public class SkiBootDaoImpl extends BaseDaoItemImpl<SkiBoot> {
    public SkiBootDaoImpl() {
        super(SkiBoot.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<SkiBoot> skiBootRoot = criteria.from(SkiBoot.class);
        Join<SkiBoot, EquipmentSize> equipmentSizeId = skiBootRoot.join("equipmentSizeId");
        criteria.select(skiBootRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
