package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.clothes.MaskDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mask;
import by.zhigalko.snow.world.service.item.BaseItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaskService implements BaseItemService<Mask> {
    private final MaskDaoImpl maskDao;

    @Autowired
    public MaskService(MaskDaoImpl maskDao) {
        this.maskDao = maskDao;
    }

    @Override
    public Mask getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }

    @Override
    public boolean save(Mask item) {
        return maskDao.save(item);
    }

    @Override
    public List<Mask> findAll(int page, int pageSize) {
        return maskDao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return maskDao.count();
    }
}
