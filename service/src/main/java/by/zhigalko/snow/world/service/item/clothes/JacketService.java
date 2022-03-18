package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.service.item.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JacketService extends BaseItemServiceImpl<Jacket> {
    private final ItemGenerator itemGenerator;

    @Autowired
    public JacketService(BaseDaoItemImpl<Jacket> dao, ItemGenerator itemGenerator) {
        super(dao);
        this.itemGenerator = itemGenerator;
    }

    @Override
    public Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return itemGenerator.setEquipmentData(request, new Pants(), equipmentSize, image);
    }
}
