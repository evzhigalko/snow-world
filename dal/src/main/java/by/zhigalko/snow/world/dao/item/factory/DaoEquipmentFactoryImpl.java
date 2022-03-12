package by.zhigalko.snow.world.dao.item.factory;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.clothes.*;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.dao.item.ski.SkiBootDaoImpl;
import by.zhigalko.snow.world.dao.item.ski.SkiDaoImpl;
import by.zhigalko.snow.world.dao.item.ski.SkiHelmetDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

@Repository("daoEquipmentFactory")
public class DaoEquipmentFactoryImpl implements DaoEquipmentFactory<Item>{
    private final ApplicationContext context;

    @Autowired
    public DaoEquipmentFactoryImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public BaseDaoItemImpl<? extends Item> getDao(Page page) {
        BaseDaoItemImpl<? extends Item> dao = null;
        switch (page) {
            case SNOWBOARD_LIST:
                dao = context.getBean("snowboardDao", SnowboardDaoImpl.class);
                break;
            case SNOWBOARD_BOOT_LIST:
                dao = context.getBean("snowboardBootDao", SnowboardBootDaoImpl.class);
                break;
            case SNOWBOARD_HELMET_LIST:
                dao = context.getBean("snowboardHelmetDao", SnowboardHelmetDaoImpl.class);
                break;
            case SKI_LIST:
                dao = context.getBean("skiDao", SkiDaoImpl.class);
                break;
            case SKI_BOOT_LIST:
                dao = context.getBean("skiBootDao", SkiBootDaoImpl.class);
                break;
            case SKI_HELMET_LIST:
                dao = context.getBean("skiHelmetDao", SkiHelmetDaoImpl.class);
                break;
            case JACKET_LIST:
                dao = context.getBean("jacketDao", JacketDaoImpl.class);
                break;
            case PANTS_LIST:
                dao = context.getBean("pantsDao", PantsDaoImpl.class);
                break;
            case MASK_LIST:
                dao = context.getBean("maskDao", MaskDaoImpl.class);
                break;
            case CAP_LIST:
                dao = context.getBean("capDao", CapDaoImpl.class);
                break;
            case MITTENS_LIST:
                dao = context.getBean("mittenDao", MittenDaoImpl.class);
                break;
            case GLOVES_LIST:
                dao = context.getBean("gloveDao", GloveDaoImpl.class);
                break;
        }
        return dao;
    }

    @Override
    public EquipmentAllSizesDao getAllSizesDao(String itemName) {
        EquipmentAllSizesDao allSizesDao = null;
        switch (itemName) {
            case "snowboard":
                allSizesDao = context.getBean("snowboardDao", SnowboardDaoImpl.class);
                break;
            case "snowboard_boot":
                allSizesDao = context.getBean("snowboardBootDao", SnowboardBootDaoImpl.class);
                break;
            case "snowboard_helmet":
                allSizesDao = context.getBean("snowboardHelmetDao", SnowboardHelmetDaoImpl.class);
        }
        return allSizesDao;
    }
}
