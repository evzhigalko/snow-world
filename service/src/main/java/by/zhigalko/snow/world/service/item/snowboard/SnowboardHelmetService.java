package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardHelmetService {
    private final SnowboardHelmetDaoImpl snowboardHelmetDao;

    @Autowired
    public SnowboardHelmetService(SnowboardHelmetDaoImpl snowboardHelmetDao) {
        this.snowboardHelmetDao = snowboardHelmetDao;
    }

    public SnowboardHelmet getItem(String maker, Gender gender, double cost,
                                 boolean availableToRental, EquipmentSize equipmentSize,
                                 Image image, ProductGroup productGroup) {
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setMaker(maker);
        snowboardHelmet.setGender(gender);
        snowboardHelmet.setCost(cost);
        snowboardHelmet.setAvailableToRental(availableToRental);
        snowboardHelmet.setEquipmentSizeId(equipmentSize);
        snowboardHelmet.setImage(image);
        snowboardHelmet.setProductName(productGroup);
        return snowboardHelmet;
    }

    public boolean save(SnowboardHelmet snowboardHelmet) {
        return snowboardHelmetDao.save(snowboardHelmet);
    }
}
