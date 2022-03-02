package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.snowboard.SnowboardBootDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SnowboardBootService implements BaseItemService<SnowboardBoot> {
    private final SnowboardBootDaoImpl snowboardBootDao;

    @Autowired
    public SnowboardBootService(SnowboardBootDaoImpl snowboardBootDao) {
        this.snowboardBootDao = snowboardBootDao;
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
    @Override
    public boolean save(SnowboardBoot snowboardBoot) {
        return snowboardBootDao.save(snowboardBoot);
    }

    @Override
    public List<SnowboardBoot> findAll(int page, int pageSize) {
        return snowboardBootDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return snowboardBootDao.count();
    }
}
