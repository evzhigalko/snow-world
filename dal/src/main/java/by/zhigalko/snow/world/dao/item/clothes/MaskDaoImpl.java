package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.clothes.Mask;

public class MaskDaoImpl extends BaseDaoItemImpl<Mask> {
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
