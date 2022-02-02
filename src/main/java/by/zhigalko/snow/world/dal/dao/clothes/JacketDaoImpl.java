package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Jacket;

public class JacketDaoImpl extends BaseDaoEquipmentImpl<Jacket> {
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
