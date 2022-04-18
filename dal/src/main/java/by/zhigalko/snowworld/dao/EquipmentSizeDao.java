package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.EquipmentSize;

public interface EquipmentSizeDao {
    /**
     * Find equipment size from data source
     * @param id String
     * @return EquipmentSize entity
     */
    EquipmentSize findById(String id);
}
