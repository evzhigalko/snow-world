package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.ski.SkiPole;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiPoleService extends BaseItemServiceImpl<SkiPole> {
    @Autowired
    public SkiPoleService(BaseDaoItemImpl<SkiPole> dao) {
        super(dao);
    }

    @Override
    public SkiPole getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SkiPole skiPole = new SkiPole();
        skiPole.setMaker(request.getParameter("maker"));
        skiPole.setGender(Gender.valueOf(request.getParameter("gender")));
        skiPole.setCost(Double.parseDouble(request.getParameter("cost")));
        skiPole.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        skiPole.setEquipmentSizeId(equipmentSize);
        skiPole.setImage(image);
        skiPole.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return skiPole;
    }
}
