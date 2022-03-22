package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Glove;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import javax.servlet.http.HttpServletRequest;
import by.zhigalko.snow.world.service.item.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GloveService extends BaseItemServiceImpl<Glove> {
    private final ItemGenerator itemGenerator;

    @Autowired
    public GloveService(ItemRepository<Glove> itemRepository, EquipmentSizeRepository equipmentSizeRepository, ItemGenerator itemGenerator) {
        super(itemRepository, equipmentSizeRepository);
        this.itemGenerator = itemGenerator;
    }

    @Override
    public Item getItem(HttpServletRequest request, EquipmentSize equipmentSize, Image image) {
        return itemGenerator.setEquipmentData(request, new Glove(), equipmentSize, image);
    }
}
