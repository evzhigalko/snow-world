package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Pants;

public class PantsDaoImpl extends BaseDaoEquipmentImpl<Pants> {
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
