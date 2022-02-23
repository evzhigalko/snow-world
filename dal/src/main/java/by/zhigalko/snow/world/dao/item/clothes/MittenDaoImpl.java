package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import org.springframework.stereotype.Repository;

@Repository("mittenDao")
public class MittenDaoImpl extends BaseDaoItemImpl<Mitten> {
    public MittenDaoImpl() {
        super(Mitten.class);
    }
}
