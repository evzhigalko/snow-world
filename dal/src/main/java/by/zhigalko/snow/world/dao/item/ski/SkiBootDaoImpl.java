package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.SkiBoot;

public class SkiBootDaoImpl extends BaseDaoItemImpl<SkiBoot> {
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
