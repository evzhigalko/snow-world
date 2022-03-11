package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PantsService extends BaseItemServiceImpl<Pants> {
    @Autowired
    public PantsService(BaseDaoItemImpl<Pants> dao) {
        super(dao);
    }

    @Override
    public Pants getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
