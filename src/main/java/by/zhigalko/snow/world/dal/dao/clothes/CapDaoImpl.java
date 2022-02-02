package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Cap;

public class CapDaoImpl extends BaseDaoEquipmentImpl<Cap> {
    private static volatile CapDaoImpl instance = null;

    private CapDaoImpl() {
        super(Cap.class);
    }

    public static CapDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CapDaoImpl.class) {
                if (instance == null) {
                    instance = new CapDaoImpl();
                }
            }
        }
        return instance;
    }
}
