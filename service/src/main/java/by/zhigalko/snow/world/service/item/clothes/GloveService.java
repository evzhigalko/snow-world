package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.GloveDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Glove;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GloveService implements BaseItemService<Glove> {
    private final GloveDaoImpl gloveDao;

    @Autowired
    public GloveService(GloveDaoImpl gloveDao) {
        this.gloveDao = gloveDao;
    }

    @Override
    public Glove getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Glove item) {
        return gloveDao.save(item);
    }

    @Override
    public List<Glove> findAll(int page, int pageSize) {
        return gloveDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return gloveDao.count();
    }
}
