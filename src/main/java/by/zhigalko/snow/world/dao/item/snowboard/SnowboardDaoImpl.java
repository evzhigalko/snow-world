package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;

public class SnowboardDaoImpl extends BaseDaoItemImpl<Snowboard> {
    private static volatile SnowboardDaoImpl instance = null;

    private SnowboardDaoImpl() {
        super(Snowboard.class);
    }

    public static SnowboardDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SnowboardDaoImpl.class) {
                if (instance == null) {
                    instance = new SnowboardDaoImpl();
                }
            }
        }
        return instance;
    }
}
