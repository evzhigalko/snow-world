package by.zhigalko.snow.world.service.common.equipment_size;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSizeService {
    private final EquipmentSizeRepository equipmentSizeRepository;

    @Autowired
    public EquipmentSizeService(EquipmentSizeRepository equipmentSizeRepository) {
        this.equipmentSizeRepository = equipmentSizeRepository;
    }

    public EquipmentSize findEquipmentSizeById(String id) {
        return equipmentSizeRepository.getById(id);
    }
}
