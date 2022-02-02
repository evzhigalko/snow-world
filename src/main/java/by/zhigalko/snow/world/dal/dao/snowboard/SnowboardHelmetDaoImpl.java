package by.zhigalko.snow.world.dal.dao.snowboard;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.snowboard.SnowboardHelmet;

public class SnowboardHelmetDaoImpl extends BaseDaoEquipmentImpl<SnowboardHelmet> {
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
