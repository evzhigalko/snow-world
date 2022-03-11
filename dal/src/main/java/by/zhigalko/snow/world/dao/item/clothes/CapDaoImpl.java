package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Cap;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("capDao")
public class CapDaoImpl extends BaseDaoItemImpl<Cap> {
    public CapDaoImpl() {
        super(Cap.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
