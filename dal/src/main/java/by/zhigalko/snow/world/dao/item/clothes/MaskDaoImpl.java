package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Mask;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("maskDao")
public class MaskDaoImpl extends BaseDaoItemImpl<Mask> {
    public MaskDaoImpl() {
        super(Mask.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
