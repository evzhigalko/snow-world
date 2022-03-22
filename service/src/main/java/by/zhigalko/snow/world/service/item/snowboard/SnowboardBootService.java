package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.*;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.service.item.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardBootService extends BaseItemServiceImpl<SnowboardBoot> {
    private final ItemGenerator itemGenerator;

    @Autowired
    public SnowboardBootService(ItemRepository<SnowboardBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository, ItemGenerator itemGenerator) {
        super(itemRepository, equipmentSizeRepository);
        this.itemGenerator = itemGenerator;
    }

    public Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        SnowboardBoot snowboardBoot = new SnowboardBoot();
        snowboardBoot.setLacingSystem(LacingSystem.valueOf(request.getParameter("lacing_system")));
        return itemGenerator.setEquipmentData(request, snowboardBoot, equipmentSize, image);
    }
}
