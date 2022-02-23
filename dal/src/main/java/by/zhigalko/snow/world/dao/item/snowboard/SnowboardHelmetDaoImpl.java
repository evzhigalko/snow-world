package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import org.springframework.stereotype.Repository;

@Repository("snowboardHelmetDao")
public class SnowboardHelmetDaoImpl extends BaseDaoItemImpl<SnowboardHelmet> {
    public SnowboardHelmetDaoImpl() {
        super(SnowboardHelmet.class);
    }
}
