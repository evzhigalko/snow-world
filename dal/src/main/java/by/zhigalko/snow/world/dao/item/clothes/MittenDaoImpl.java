package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Mitten;

public class MittenDaoImpl extends BaseDaoItemImpl<Mitten> {
    private static volatile MittenDaoImpl instance = null;

    private MittenDaoImpl() {
        super(Mitten.class);
    }

    public static MittenDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MittenDaoImpl.class) {
                if (instance == null) {
                    instance = new MittenDaoImpl();
                }
            }
        }
        return instance;
    }
}
