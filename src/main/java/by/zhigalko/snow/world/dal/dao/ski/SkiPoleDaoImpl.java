package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.ski.SkiPole;

public class SkiPoleDaoImpl extends BaseDaoEquipmentImpl<SkiPole> {
    private static volatile SkiPoleDaoImpl instance = null;

    private SkiPoleDaoImpl() {
        super(SkiPole.class);
    }

    public static SkiPoleDaoImpl getInstance() {
        if (instance == null) {
            synchronized (SkiPoleDaoImpl.class) {
                if (instance == null) {
                    instance = new SkiPoleDaoImpl();
                }
            }
        }
        return instance;
    }
}
