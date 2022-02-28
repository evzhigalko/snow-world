package by.zhigalko.snow.world.service.common.equipment_size;

import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentSizeDao;
import by.zhigalko.snow.world.entity.EquipmentSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSizeService {
    private final EquipmentSizeDao equipmentSizeDao;

    @Autowired
    public EquipmentSizeService(EquipmentSizeDao equipmentSizeDao) {
        this.equipmentSizeDao = equipmentSizeDao;
    }

    public EquipmentSize findEquipmentSizeById(String id) {
        return equipmentSizeDao.findById(id);
    }
}
