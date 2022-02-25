package by.zhigalko.snow.world.dao.item.equipment_size;

import by.zhigalko.snow.world.entity.EquipmentSize;
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
