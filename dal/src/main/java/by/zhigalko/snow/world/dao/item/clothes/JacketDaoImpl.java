package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Jacket;

public class JacketDaoImpl extends BaseDaoItemImpl<Jacket> {
    private static volatile JacketDaoImpl instance = null;

    private JacketDaoImpl() {
        super(Jacket.class);
    }

    public static JacketDaoImpl getInstance() {
        if (instance == null) {
            synchronized (JacketDaoImpl.class) {
                if (instance == null) {
                    instance = new JacketDaoImpl();
                }
            }
        }
        return instance;
    }
}
