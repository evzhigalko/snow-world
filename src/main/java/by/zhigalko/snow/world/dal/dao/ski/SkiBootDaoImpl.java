package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.ski.SkiBoot;

public class SkiBootDaoImpl extends BaseDaoEquipmentImpl<SkiBoot> {
    private static volatile SkiBootDaoImpl instance = null;

    private SkiBootDaoImpl() {
        super(SkiBoot.class);
    }

    public static SkiBootDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SkiBootDaoImpl.class) {
                if (instance == null) {
                    instance = new SkiBootDaoImpl();
                }
            }
        }
        return instance;
    }
}
