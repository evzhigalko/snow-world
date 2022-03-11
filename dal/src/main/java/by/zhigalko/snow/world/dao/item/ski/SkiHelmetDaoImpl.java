package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("skiHelmetDao")
public class SkiHelmetDaoImpl extends BaseDaoItemImpl<SkiHelmet> {
    public SkiHelmetDaoImpl() {
        super(SkiHelmet.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
