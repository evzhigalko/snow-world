package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiBootService extends BaseItemServiceImpl<SkiBoot> {
    @Autowired
    public SkiBootService(BaseDaoItemImpl<SkiBoot> dao) {
        super(dao);
    }

    @Override
    public SkiBoot getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
