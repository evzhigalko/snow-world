package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Mask;
import org.springframework.stereotype.Repository;

@Repository("maskDao")
public class MaskDaoImpl extends BaseDaoItemImpl<Mask> {
    public MaskDaoImpl() {
        super(Mask.class);
    }
}
