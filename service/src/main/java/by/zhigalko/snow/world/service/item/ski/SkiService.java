package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.service.item.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiService extends BaseItemServiceImpl<Ski> {
    private final ItemGenerator itemGenerator;

    @Autowired
    public SkiService(BaseDaoItemImpl<Ski> dao, ItemGenerator itemGenerator) {
        super(dao);
        this.itemGenerator = itemGenerator;
    }

    @Override
    public Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        Ski ski = new Ski();
        ski.setRidingLevel(RidingLevel.valueOf(request.getParameter("riding_level")));
        return itemGenerator.setEquipmentData(request, ski, equipmentSize, image);
    }
}
