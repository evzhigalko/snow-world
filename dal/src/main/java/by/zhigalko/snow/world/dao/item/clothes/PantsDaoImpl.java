package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Pants;
import org.springframework.stereotype.Repository;

@Repository("pantsDao")
public class PantsDaoImpl extends BaseDaoItemImpl<Pants> {
    public PantsDaoImpl() {
        super(Pants.class);
    }
}
