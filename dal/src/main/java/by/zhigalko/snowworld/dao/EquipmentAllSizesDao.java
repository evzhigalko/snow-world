package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.EquipmentSize;

import java.util.List;

/**
 * DAO for EquipmentSize
 */
public interface EquipmentAllSizesDao {
    /**
     * Find list of EquipmentSize by item
     * @return List of EquipmentSize
     */
    List<EquipmentSize> findAllSizes();
}
