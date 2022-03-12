package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardBootService extends BaseItemServiceImpl<SnowboardBoot> {
    @Autowired
    public SnowboardBootService(BaseDaoItemImpl<SnowboardBoot> dao) {
        super(dao);
    }

    public SnowboardBoot getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SnowboardBoot snowboardBoot = new SnowboardBoot();
        snowboardBoot.setMaker(request.getParameter("maker"));
        snowboardBoot.setGender(Gender.valueOf(request.getParameter("gender")));
        snowboardBoot.setCost(Double.parseDouble(request.getParameter("cost")));
        snowboardBoot.setAvailableToRental(Boolean.parseBoolean(request.getParameter("available_to_rental")));
        snowboardBoot.setLacingSystem(LacingSystem.valueOf(request.getParameter("lacing_system")));
        snowboardBoot.setEquipmentSizeId(equipmentSize);
        snowboardBoot.setImage(image);
        snowboardBoot.setProductName(ProductGroup.valueOf(request.getParameter("product_group")));
        return snowboardBoot;
    }
}
