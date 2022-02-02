package by.zhigalko.snow.world.dal.dao.snowboard;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.snowboard.SnowboardBoot;

public class SnowboardBootDaoImpl extends BaseDaoEquipmentImpl<SnowboardBoot> {
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
