package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JacketService extends BaseItemServiceImpl<Jacket> {
    @Autowired
    public JacketService(BaseDaoItemImpl<Jacket> dao) {
        super(dao);
    }

    @Override
    public Jacket getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return null;
    }
}
