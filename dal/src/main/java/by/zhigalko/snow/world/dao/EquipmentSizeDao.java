package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.entity.EquipmentSize;

public interface EquipmentSizeDao {
    /**
     * Find equipment size from data source
     * @param id String
     * @return EquipmentSize entity
     */
    EquipmentSize findById(String id);
}
