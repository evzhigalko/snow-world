package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.ski.Ski;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("skiDao")
public class SkiDaoImpl extends BaseDaoItemImpl<Ski> {
    public SkiDaoImpl() {
        super(Ski.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
