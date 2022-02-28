package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardBootService {
    private final SnowboardBootDaoImpl snowboardBootDao;

    @Autowired
    public SnowboardBootService(SnowboardBootDaoImpl snowboardBootDao) {
        this.snowboardBootDao = snowboardBootDao;
    }

    public SnowboardBoot getItem(String maker, Gender gender, double cost,
                                 boolean availableToRental, LacingSystem lacingSystem, EquipmentSize equipmentSize,
                                 Image image, ProductGroup productGroup) {
        SnowboardBoot snowboardBoot = new SnowboardBoot();
        snowboardBoot.setMaker(maker);
        snowboardBoot.setGender(gender);
        snowboardBoot.setCost(cost);
        snowboardBoot.setAvailableToRental(availableToRental);
        snowboardBoot.setLacingSystem(lacingSystem);
        snowboardBoot.setEquipmentSizeId(equipmentSize);
        snowboardBoot.setImage(image);
        snowboardBoot.setProductName(productGroup);
        return snowboardBoot;
    }

    public boolean save(SnowboardBoot snowboardBoot) {
        return snowboardBootDao.save(snowboardBoot);
    }
}
