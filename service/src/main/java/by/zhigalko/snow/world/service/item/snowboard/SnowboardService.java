package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardService extends BaseItemServiceImpl<Snowboard> {
    @Autowired
    public SnowboardService(BaseDaoItemImpl<Snowboard> dao) {
        super(dao);
    }

    public Snowboard getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        Snowboard snowboard = new Snowboard();
        snowboard.setMaker(request.getParameter("maker"));
        snowboard.setHardnessLevel(HardnessLevel.valueOf(request.getParameter("hardness_level")));
        snowboard.setRidingLevel(RidingLevel.valueOf(request.getParameter("riding_level")));
        snowboard.setGender(Gender.valueOf(request.getParameter("gender")));
        snowboard.setCost(Double.parseDouble(request.getParameter("cost")));
        snowboard.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        snowboard.setEquipmentSizeId(equipmentSize);
        snowboard.setImage(image);
        snowboard.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return snowboard;
    }
}
