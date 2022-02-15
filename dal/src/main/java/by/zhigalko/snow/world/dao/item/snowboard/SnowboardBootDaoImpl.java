package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;

public class SnowboardBootDaoImpl extends BaseDaoItemImpl<SnowboardBoot> {
    private static volatile SnowboardBootDaoImpl instance = null;

    private SnowboardBootDaoImpl() {
        super(SnowboardBoot.class);
    }

    public static SnowboardBootDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SnowboardDaoImpl.class) {
                if (instance == null) {
                    instance = new SnowboardBootDaoImpl();
                }
            }
        }
        return instance;
    }
}
