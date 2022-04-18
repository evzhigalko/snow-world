package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.dao.impl.BaseDaoItemImpl;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.Page;

public interface DaoEquipmentFactory<T extends Item> {
    BaseDaoItemImpl<? extends Item> getDao(Page page);
    EquipmentAllSizesDao getAllSizesDao(String itemName);
}
