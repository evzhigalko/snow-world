package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Pants;

public class PantsDaoImpl extends BaseDaoItemImpl<Pants> {
    private static volatile PantsDaoImpl instance = null;

    private PantsDaoImpl() {
        super(Pants.class);
    }

    public static PantsDaoImpl getInstance() {
        if (instance == null) {
            synchronized (PantsDaoImpl.class) {
                if (instance == null) {
                    instance = new PantsDaoImpl();
                }
            }
        }
        return instance;
    }
}
