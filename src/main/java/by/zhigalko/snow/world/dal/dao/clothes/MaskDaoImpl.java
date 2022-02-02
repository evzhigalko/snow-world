package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.clothes.Mask;

public class MaskDaoImpl extends BaseDaoEquipmentImpl<Mask> {
    private static volatile MaskDaoImpl instance = null;

    private MaskDaoImpl() {
        super(Mask.class);
    }

    public static MaskDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MaskDaoImpl.class) {
                if (instance == null) {
                    instance = new MaskDaoImpl();
                }
            }
        }
        return instance;
    }
}
