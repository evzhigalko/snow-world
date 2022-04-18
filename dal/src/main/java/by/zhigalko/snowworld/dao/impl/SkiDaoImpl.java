package by.zhigalko.snowworld.dao.impl;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Ski;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("skiDao")
public class SkiDaoImpl extends BaseDaoItemImpl<Ski> {
    public SkiDaoImpl() {
        super(Ski.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<EquipmentSize> equipmentSizeRoot = criteria.from(EquipmentSize.class);
        criteria.select(equipmentSizeRoot)
                .where(cb.like(equipmentSizeRoot.get("equipmentSizeId"), "SK%"))
                .orderBy(cb.asc(equipmentSizeRoot.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
