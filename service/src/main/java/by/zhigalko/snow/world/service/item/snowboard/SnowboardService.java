package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.service.item.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardService extends BaseItemServiceImpl<Snowboard> {
    private final ItemGenerator itemGenerator;

    @Autowired
    public SnowboardService(ItemRepository<Snowboard> itemRepository, EquipmentSizeRepository equipmentSizeRepository, ItemGenerator itemGenerator) {
        super(itemRepository, equipmentSizeRepository);
        this.itemGenerator = itemGenerator;
    }

    public Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        Snowboard snowboard = new Snowboard();
        snowboard.setHardnessLevel(HardnessLevel.valueOf(request.getParameter("hardness_level")));
        snowboard.setRidingLevel(RidingLevel.valueOf(request.getParameter("riding_level")));
        return itemGenerator.setEquipmentData(request, snowboard, equipmentSize, image);
    }
}
