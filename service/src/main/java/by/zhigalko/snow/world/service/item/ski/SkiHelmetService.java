package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.ski.SkiHelmetDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkiHelmetService implements BaseItemService<SkiHelmet> {
    private final SkiHelmetDaoImpl skiHelmetDao;

    @Autowired
    public SkiHelmetService(SkiHelmetDaoImpl skiHelmetDao) {
        this.skiHelmetDao = skiHelmetDao;
    }

    @Override
    public SkiHelmet getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(SkiHelmet item) {
        return skiHelmetDao.save(item);
    }

    @Override
    public List<SkiHelmet> findAll(int page, int pageSize) {
        return skiHelmetDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return skiHelmetDao.count();
    }
}
