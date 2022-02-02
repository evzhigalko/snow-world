package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Mitten;

public class MittenDaoImpl extends BaseDaoEquipmentImpl<Mitten> {
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
