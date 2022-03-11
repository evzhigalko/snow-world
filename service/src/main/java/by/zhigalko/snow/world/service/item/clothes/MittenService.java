package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MittenService extends BaseItemServiceImpl<Mitten> {
    @Autowired
    public MittenService(BaseDaoItemImpl<Mitten> dao) {
        super(dao);
    }

    @Override
    public Mitten getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
