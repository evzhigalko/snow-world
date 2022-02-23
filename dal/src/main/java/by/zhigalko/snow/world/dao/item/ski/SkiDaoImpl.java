package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.Ski;
import org.springframework.stereotype.Repository;

@Repository("skiDao")
public class SkiDaoImpl extends BaseDaoItemImpl<Ski> {
    public SkiDaoImpl() {
        super(Ski.class);
    }
}
