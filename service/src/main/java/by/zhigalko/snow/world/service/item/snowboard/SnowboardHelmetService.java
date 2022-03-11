package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardHelmetService extends BaseItemServiceImpl<SnowboardHelmet> {
    @Autowired
    public SnowboardHelmetService(BaseDaoItemImpl<SnowboardHelmet> dao) {
        super(dao);
    }

    public SnowboardHelmet getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setMaker(request.getParameter("maker"));
        snowboardHelmet.setGender(Gender.valueOf(request.getParameter("gender")));
        snowboardHelmet.setCost(Double.parseDouble(request.getParameter("cost")));
        snowboardHelmet.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        snowboardHelmet.setEquipmentSizeId(equipmentSize);
        snowboardHelmet.setImage(image);
        snowboardHelmet.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return snowboardHelmet;
    }
}
