package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;

public class SkiHelmetDaoImpl extends BaseDaoItemImpl<SkiHelmet> {
    private static volatile SkiHelmetDaoImpl instance = null;

    private SkiHelmetDaoImpl() {
        super(SkiHelmet.class);
    }

    public static SkiHelmetDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SkiHelmetDaoImpl.class) {
                if (instance == null) {
                    instance = new SkiHelmetDaoImpl();
                }
            }
        }
        return instance;
    }
}
