package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.MittenDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MittenService implements BaseItemService<Mitten> {
    private final MittenDaoImpl mittenDao;

    @Autowired
    public MittenService(MittenDaoImpl mittenDao) {
        this.mittenDao = mittenDao;
    }

    @Override
    public Mitten getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Mitten item) {
        return mittenDao.save(item);
    }

    @Override
    public List<Mitten> findAll(int page, int pageSize) {
        return mittenDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return mittenDao.count();
    }
}
