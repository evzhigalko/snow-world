package by.zhigalko.snowworld.dao.impl;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Glove;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("gloveDao")
public class GloveDaoImpl extends BaseDaoItemImpl<Glove> {
    public GloveDaoImpl() {
        super(Glove.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<Glove> gloveRoot = criteria.from(Glove.class);
        Join<Glove, EquipmentSize> equipmentSizeId = gloveRoot.join("equipmentSizeId");
        criteria.select(gloveRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
