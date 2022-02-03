package by.zhigalko.snow.world.dal.dao.factory;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.entity.BaseEntity;
import by.zhigalko.snow.world.dal.entity.Item;
import by.zhigalko.snow.world.dal.entity.enums.Page;

public interface DaoEquipmentFactory<T extends BaseEntity> {
    BaseDaoEquipmentImpl<Item> getDao(Page page);
}
