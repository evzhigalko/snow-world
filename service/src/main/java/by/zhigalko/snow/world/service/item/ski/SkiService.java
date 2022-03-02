package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.ski.SkiDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkiService implements BaseItemService<Ski> {
    private final SkiDaoImpl skiDao;

    @Autowired
    public SkiService(SkiDaoImpl skiDao) {
        this.skiDao = skiDao;
    }

    @Override
    public Ski getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Ski item) {
        return skiDao.save(item);
    }

    @Override
    public List<Ski> findAll(int page, int pageSize) {
        return skiDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return skiDao.count();
    }
}
