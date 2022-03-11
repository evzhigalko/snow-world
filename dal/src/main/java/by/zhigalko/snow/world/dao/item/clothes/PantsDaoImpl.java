package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Pants;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("pantsDao")
public class PantsDaoImpl extends BaseDaoItemImpl<Pants> {
    public PantsDaoImpl() {
        super(Pants.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
