package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.clothes.Glove;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("gloveDao")
public class GloveDaoImpl extends BaseDaoItemImpl<Glove> {
    public GloveDaoImpl() {
        super(Glove.class);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return null;
    }
}
