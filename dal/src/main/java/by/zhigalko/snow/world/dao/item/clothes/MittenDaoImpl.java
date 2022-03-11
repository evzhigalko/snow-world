package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("mittenDao")
public class MittenDaoImpl extends BaseDaoItemImpl<Mitten> {
    public MittenDaoImpl() {
        super(Mitten.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
