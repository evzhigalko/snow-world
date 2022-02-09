package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Glove;

public class GloveDaoImpl extends BaseDaoItemImpl<Glove> {
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
