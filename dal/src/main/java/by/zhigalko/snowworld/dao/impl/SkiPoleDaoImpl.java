package by.zhigalko.snowworld.dao.impl;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.SkiPole;
import javax.persistence.criteria.*;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("skiPoleDao")
public class SkiPoleDaoImpl extends BaseDaoItemImpl<SkiPole> {
    public SkiPoleDaoImpl() {
        super(SkiPole.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<SkiPole> skiPole = criteria.from(SkiPole.class);
        Join<SkiPole, EquipmentSize> equipmentSizeId = skiPole.join("equipmentSizeId");
        criteria.select(skiPole.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("userMinHeight"))
                .orderBy(cb.asc(equipmentSizeId.get("userMinHeight")));
        return session.createQuery(criteria).getResultList();
    }
}
