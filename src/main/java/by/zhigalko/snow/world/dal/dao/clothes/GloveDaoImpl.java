package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Glove;

public class GloveDaoImpl extends BaseDaoEquipmentImpl<Glove> {
    private static volatile GloveDaoImpl instance = null;

    private GloveDaoImpl() {
        super(Glove.class);
    }

    public static GloveDaoImpl getInstance() {
        if (instance == null) {
            synchronized (GloveDaoImpl.class) {
                if (instance == null) {
                    instance = new GloveDaoImpl();
                }
            }
        }
        return instance;
    }
}
