package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardHelmetRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.mapper.SnowboardHelmetMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardHelmetService extends BaseItemServiceImpl<SnowboardHelmet> {
    private final SnowboardHelmetMapper snowboardHelmetMapper;

    @Autowired
    public SnowboardHelmetService(ItemRepository<SnowboardHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SnowboardHelmetMapper snowboardHelmetMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardHelmetMapper = snowboardHelmetMapper;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        SnowboardHelmet snowboardHelmet = snowboardHelmetMapper.snowboardHelmetRequestToSnowboardHelmet((SnowboardHelmetRequest) itemRequest);
        snowboardHelmet.setImage(image);
        return snowboardHelmet;
    }
}
