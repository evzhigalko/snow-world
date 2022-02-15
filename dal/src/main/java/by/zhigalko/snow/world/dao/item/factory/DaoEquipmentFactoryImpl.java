package by.zhigalko.snow.world.dao.item.factory;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.clothes.*;
import by.zhigalko.snow.world.dao.item.ski.SkiBootDaoImpl;
import by.zhigalko.snow.world.dao.item.ski.SkiDaoImpl;
import by.zhigalko.snow.world.dao.item.ski.SkiHelmetDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;

public class DaoEquipmentFactoryImpl implements DaoEquipmentFactory<Item>{
    private static volatile DaoEquipmentFactoryImpl instance = null;

    private DaoEquipmentFactoryImpl() {}

    public static DaoEquipmentFactoryImpl getInstance() {
        if (instance == null) {
            synchronized (DaoEquipmentFactoryImpl.class) {
                if (instance == null) {
                    instance = new DaoEquipmentFactoryImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public BaseDaoItemImpl<Item> getDao(Page page) {
        BaseDaoItemImpl dao = null;
        switch (page) {
            case SNOWBOARD_LIST:
                dao = SnowboardDaoImpl.getInstance();
                break;
            case SNOWBOARD_BOOT_LIST:
                dao = SnowboardBootDaoImpl.getInstance();
                break;
            case SNOWBOARD_HELMET_LIST:
                dao = SnowboardHelmetDaoImpl.getInstance();
                break;
            case SKI_LIST:
                dao = SkiDaoImpl.getInstance();
                break;
            case SKI_BOOT_LIST:
                dao = SkiBootDaoImpl.getInstance();
                break;
            case SKI_HELMET_LIST:
                dao = SkiHelmetDaoImpl.getInstance();
                break;
            case JACKET_LIST:
                dao = JacketDaoImpl.getInstance();
                break;
            case PANTS_LIST:
                dao = PantsDaoImpl.getInstance();
                break;
            case MASK_LIST:
                dao = MaskDaoImpl.getInstance();
                break;
            case CAP_LIST:
                dao = CapDaoImpl.getInstance();
                break;
            case MITTENS_LIST:
                dao = MittenDaoImpl.getInstance();
                break;
            case GLOVES_LIST:
                dao = GloveDaoImpl.getInstance();
                break;
        }
        return dao;
    }
}
