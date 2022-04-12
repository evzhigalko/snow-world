package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.service.EquipmentSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSizeServiceImpl implements EquipmentSizeService {
    private final EquipmentSizeRepository equipmentSizeRepository;

    @Autowired
    public EquipmentSizeServiceImpl(EquipmentSizeRepository equipmentSizeRepository) {
        this.equipmentSizeRepository = equipmentSizeRepository;
    }

    public EquipmentSize findEquipmentSizeById(String id) {
        return equipmentSizeRepository.getById(id);
    }
}
