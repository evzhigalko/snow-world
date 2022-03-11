package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("skiBootDao")
public class SkiBootDaoImpl extends BaseDaoItemImpl<SkiBoot> {
    public SkiBootDaoImpl() {
        super(SkiBoot.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
