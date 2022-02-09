package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.Ski;

public class SkiDaoImpl extends BaseDaoItemImpl<Ski> {
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
