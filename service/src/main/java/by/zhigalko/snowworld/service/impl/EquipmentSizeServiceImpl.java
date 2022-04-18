package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
