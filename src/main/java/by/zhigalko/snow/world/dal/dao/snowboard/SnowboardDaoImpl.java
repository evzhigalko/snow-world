package by.zhigalko.snow.world.dal.dao.snowboard;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.snowboard.Snowboard;

public class SnowboardDaoImpl extends BaseDaoEquipmentImpl<Snowboard> {
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
