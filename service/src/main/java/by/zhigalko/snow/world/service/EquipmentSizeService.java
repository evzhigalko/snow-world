package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.entity.EquipmentSize;

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
