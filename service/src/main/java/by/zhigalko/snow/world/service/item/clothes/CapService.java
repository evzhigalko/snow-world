package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CapService extends BaseItemServiceImpl<Cap> {
    public CapService(BaseDaoItemImpl<Cap> dao) {
        super(dao);
    }

    @Override
    public Cap getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
