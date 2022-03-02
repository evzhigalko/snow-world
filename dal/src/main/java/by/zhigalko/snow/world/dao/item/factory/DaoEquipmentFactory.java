package by.zhigalko.snow.world.dao.item.factory;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentAllSizesDao;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Page;

public interface DaoEquipmentFactory<T extends Item> {
    BaseDaoItemImpl<? extends Item> getDao(Page page);
    EquipmentAllSizesDao getAllSizesDao(String itemName);
}
