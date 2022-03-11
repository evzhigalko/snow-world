package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("jacketDao")
public class JacketDaoImpl extends BaseDaoItemImpl<Jacket> {
    public JacketDaoImpl() {
        super(Jacket.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
