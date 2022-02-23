package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import org.springframework.stereotype.Repository;

@Repository("snowboardBootDao")
public class SnowboardBootDaoImpl extends BaseDaoItemImpl<SnowboardBoot> {
    public SnowboardBootDaoImpl() {
        super(SnowboardBoot.class);
    }
}
