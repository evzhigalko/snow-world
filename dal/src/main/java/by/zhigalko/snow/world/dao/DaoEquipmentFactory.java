package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.dao.impl.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.model.Page;

public interface DaoEquipmentFactory<T extends Item> {
    BaseDaoItemImpl<? extends Item> getDao(Page page);
    EquipmentAllSizesDao getAllSizesDao(String itemName);
}
