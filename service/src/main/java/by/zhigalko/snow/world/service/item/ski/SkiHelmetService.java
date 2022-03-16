package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiHelmetService extends BaseItemServiceImpl<SkiHelmet> {
    @Autowired
    public SkiHelmetService(BaseDaoItemImpl<SkiHelmet> dao) {
        super(dao);
    }

    @Override
    public SkiHelmet getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SkiHelmet skiHelmet = new SkiHelmet();
        skiHelmet.setMaker(request.getParameter("maker"));
        skiHelmet.setGender(Gender.valueOf(request.getParameter("gender")));
        skiHelmet.setCost(Double.parseDouble(request.getParameter("cost")));
        skiHelmet.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        skiHelmet.setEquipmentSizeId(equipmentSize);
        skiHelmet.setImage(image);
        skiHelmet.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return skiHelmet;
    }
}
