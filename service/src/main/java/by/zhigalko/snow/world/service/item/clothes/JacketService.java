package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.JacketDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JacketService implements BaseItemService<Jacket> {
    private final JacketDaoImpl jacketDao;

    @Autowired
    public JacketService(JacketDaoImpl jacketDao) {
        this.jacketDao = jacketDao;
    }

    @Override
    public Jacket getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Jacket item) {
        return jacketDao.save(item);
    }

    @Override
    public List<Jacket> findAll(int page, int pageSize) {
        return jacketDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return jacketDao.count();
    }
}
