package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.ski.SkiHelmet;

public class SkiHelmetDaoImpl extends BaseDaoEquipmentImpl<SkiHelmet> {
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
