package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.PantsDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PantsService implements BaseItemService<Pants> {
    private final PantsDaoImpl pantsDao;

    @Autowired
    public PantsService(PantsDaoImpl pantsDao) {
        this.pantsDao = pantsDao;
    }

    @Override
    public Pants getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Pants item) {
        return pantsDao.save(item);
    }

    @Override
    public List<Pants> findAll(int page, int pageSize) {
        return pantsDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return pantsDao.count();
    }
}
