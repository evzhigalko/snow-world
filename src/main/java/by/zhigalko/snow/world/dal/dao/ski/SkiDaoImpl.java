package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.ski.Ski;

public class SkiDaoImpl extends BaseDaoEquipmentImpl<Ski> {
    private static volatile SkiDaoImpl instance = null;

    private SkiDaoImpl() {
        super(Ski.class);
    }

    public static SkiDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SkiDaoImpl.class) {
                if (instance == null) {
                    instance = new SkiDaoImpl();
                }
            }
        }
        return instance;
    }
}
