package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardService {
    private final SnowboardDaoImpl snowboardDao;

    @Autowired
    public SnowboardService(SnowboardDaoImpl snowboardDao) {
        this.snowboardDao = snowboardDao;
    }

    public Snowboard getItem(String maker, HardnessLevel hardnessLevel,
                             RidingLevel ridingLevel, Gender gender, double cost,
                             boolean availableToRental, EquipmentSize equipmentSize,
                             Image image, ProductGroup productGroup) {
        Snowboard snowboard = new Snowboard();
        snowboard.setMaker(maker);
        snowboard.setHardnessLevel(hardnessLevel);
        snowboard.setRidingLevel(ridingLevel);
        snowboard.setGender(gender);
        snowboard.setCost(cost);
        snowboard.setAvailableToRental(availableToRental);
        snowboard.setEquipmentSizeId(equipmentSize);
        snowboard.setImage(image);
        snowboard.setProductName(productGroup);
        return snowboard;
    }

    public boolean save(Snowboard snowboard) {
        return snowboardDao.save(snowboard);
    }
}
