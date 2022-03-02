package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dao.item.snowboard.SnowboardHelmetDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SnowboardHelmetService implements BaseItemService<SnowboardHelmet> {
    private final SnowboardHelmetDaoImpl snowboardHelmetDao;

    @Autowired
    public SnowboardHelmetService(SnowboardHelmetDaoImpl snowboardHelmetDao) {
        this.snowboardHelmetDao = snowboardHelmetDao;
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

    @Override
    public boolean save(SnowboardHelmet snowboardHelmet) {
        return snowboardHelmetDao.save(snowboardHelmet);
    }

    @Override
    public List<SnowboardHelmet> findAll(int page, int pageSize) {
        return snowboardHelmetDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return snowboardHelmetDao.count();
    }
}
