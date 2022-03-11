package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiHelmetService extends BaseItemServiceImpl<SkiHelmet> {
    @Autowired
    public SkiHelmetService(BaseDaoItemImpl<SkiHelmet> dao) {
        super(dao);
    }

    @Override
    public SkiHelmet getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
