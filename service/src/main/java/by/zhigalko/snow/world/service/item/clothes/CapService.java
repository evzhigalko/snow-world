package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.CapDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CapService implements BaseItemService<Cap> {
    private final CapDaoImpl capDao;

    @Autowired
    public CapService(CapDaoImpl capDao) {
        this.capDao = capDao;
    }

    @Override
    public Cap getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Cap item) {
        return capDao.save(item);
    }

    @Override
    public List<Cap> findAll(int page, int pageSize) {
        return capDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return capDao.count();
    }
}
