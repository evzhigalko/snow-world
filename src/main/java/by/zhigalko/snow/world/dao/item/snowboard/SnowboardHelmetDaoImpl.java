package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;

public class SnowboardHelmetDaoImpl extends BaseDaoItemImpl<SnowboardHelmet> {
    private static volatile SnowboardHelmetDaoImpl instance = null;

    private SnowboardHelmetDaoImpl() {
        super(SnowboardHelmet.class);
    }

    public static SnowboardHelmetDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SnowboardHelmetDaoImpl.class) {
                if (instance == null) {
                    instance = new SnowboardHelmetDaoImpl();
                }
            }
        }
        return instance;
    }
}
