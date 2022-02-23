package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Cap;
import org.springframework.stereotype.Repository;

@Repository("capDao")
public class CapDaoImpl extends BaseDaoItemImpl<Cap> {
    public CapDaoImpl() {
        super(Cap.class);
    }
}
