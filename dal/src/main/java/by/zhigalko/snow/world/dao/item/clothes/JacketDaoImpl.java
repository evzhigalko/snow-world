package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import org.springframework.stereotype.Repository;

@Repository("jacketDao")
public class JacketDaoImpl extends BaseDaoItemImpl<Jacket> {
    public JacketDaoImpl() {
        super(Jacket.class);
    }
}
