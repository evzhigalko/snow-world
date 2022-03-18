package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Mask;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("maskDao")
public class MaskDaoImpl extends BaseDaoItemImpl<Mask> {
    public MaskDaoImpl() {
        super(Mask.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EquipmentSize> criteria = cb.createQuery(EquipmentSize.class);
        Root<Mask> maskRoot = criteria.from(Mask.class);
        Join<Mask, EquipmentSize> equipmentSizeId = maskRoot.join("equipmentSizeId");
        criteria.select(maskRoot.get("equipmentSizeId"))
                .groupBy(equipmentSizeId, equipmentSizeId.get("equipmentSizeId"))
                .orderBy(cb.asc(equipmentSizeId.get("equipmentSizeId")));
        return session.createQuery(criteria).getResultList();
    }
}
