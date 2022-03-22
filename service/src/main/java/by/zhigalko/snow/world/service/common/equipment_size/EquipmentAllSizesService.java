package by.zhigalko.snow.world.service.common.equipment_size;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.enums.ProductGroup;

import java.util.List;

public interface EquipmentAllSizesService {
    List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup);
}
