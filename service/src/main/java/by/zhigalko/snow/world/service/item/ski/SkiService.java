package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiService extends BaseItemServiceImpl<Ski> {
    @Autowired
    public SkiService(BaseDaoItemImpl<Ski> dao) {
        super(dao);
    }

    @Override
    public Ski getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        Ski ski = new Ski();
        ski.setMaker(request.getParameter("maker"));
        ski.setGender(Gender.valueOf(request.getParameter("gender")));
        ski.setCost(Double.parseDouble(request.getParameter("cost")));
        ski.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        ski.setEquipmentSizeId(equipmentSize);
        ski.setImage(image);
        ski.setRidingLevel(RidingLevel.valueOf(request.getParameter("riding_level")));
        ski.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return ski;
    }
}
