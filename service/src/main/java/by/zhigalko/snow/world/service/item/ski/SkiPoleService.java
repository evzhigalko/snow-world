package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.ski.SkiPoleDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.SkiPole;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkiPoleService implements BaseItemService<SkiPole> {
    private final SkiPoleDaoImpl skiPoleDao;

    @Autowired
    public SkiPoleService(SkiPoleDaoImpl skiPoleDao) {
        this.skiPoleDao = skiPoleDao;
    }

    public List<EquipmentSize> findAllSizes() {
        return skiPoleDao.findAllSizes();
    }

    @Override
    public SkiPole getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(SkiPole item) {
        return skiPoleDao.save(item);
    }

    @Override
    public List<SkiPole> findAll(int page, int pageSize) {
        return skiPoleDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return skiPoleDao.count();
    }
}
