package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.entity.EquipmentSize;

/**
 * Service for EquipmentSize
 */
public interface EquipmentSizeService {
    /**
     * Find EquipmentSize by
     * @param id {@link java.util.UUID}
     * @return {@link EquipmentSize}
     */
    EquipmentSize findEquipmentSizeById(String id);
}
