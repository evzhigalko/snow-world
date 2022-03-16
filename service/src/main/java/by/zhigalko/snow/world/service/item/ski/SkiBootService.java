package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiBootService extends BaseItemServiceImpl<SkiBoot> {
    @Autowired
    public SkiBootService(BaseDaoItemImpl<SkiBoot> dao) {
        super(dao);
    }

    @Override
    public SkiBoot getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SkiBoot skiBoot = new SkiBoot();
        skiBoot.setMaker(request.getParameter("maker"));
        skiBoot.setGender(Gender.valueOf(request.getParameter("gender")));
        skiBoot.setCost(Double.parseDouble(request.getParameter("cost")));
        skiBoot.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        skiBoot.setEquipmentSizeId(equipmentSize);
        skiBoot.setImage(image);
        skiBoot.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return skiBoot;
    }
}
