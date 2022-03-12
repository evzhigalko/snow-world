package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiService extends BaseItemServiceImpl<Ski> {
    @Autowired
    public SkiService(BaseDaoItemImpl<Ski> dao) {
        super(dao);
    }

    @Override
    public Ski getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
