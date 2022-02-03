package by.zhigalko.snow.world.dal.dao.factory;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.clothes.*;
import by.zhigalko.snow.world.dal.dao.ski.SkiBootDaoImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiDaoImpl;
import by.zhigalko.snow.world.dal.dao.ski.SkiHelmetDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.dal.entity.Item;
import by.zhigalko.snow.world.dal.entity.enums.Page;

public class DaoEquipmentFactoryImpl implements DaoEquipmentFactory<Item>{
    @Override
    public BaseDaoEquipmentImpl<Item> getDao(Page page) {
        BaseDaoEquipmentImpl dao = null;
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
