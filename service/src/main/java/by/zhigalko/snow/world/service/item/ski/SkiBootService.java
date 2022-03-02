package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.ski.SkiBootDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkiBootService implements BaseItemService<SkiBoot> {
    private final SkiBootDaoImpl skiBootDao;

    @Autowired
    public SkiBootService(SkiBootDaoImpl skiBootDao) {
        this.skiBootDao = skiBootDao;
    }

    @Override
    public SkiBoot getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(SkiBoot item) {
        return skiBootDao.save(item);
    }

    @Override
    public List<SkiBoot> findAll(int page, int pageSize) {
        return skiBootDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return skiBootDao.count();
    }
}
