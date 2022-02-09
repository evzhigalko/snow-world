package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Cap;

public class CapDaoImpl extends BaseDaoItemImpl<Cap> {
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
