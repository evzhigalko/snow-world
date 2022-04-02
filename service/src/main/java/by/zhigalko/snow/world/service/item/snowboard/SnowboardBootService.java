package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardBootRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.mapper.SnowboardBootMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardBootService extends BaseItemServiceImpl<SnowboardBoot> {
     private final SnowboardBootMapper snowboardBootMapper;

    @Autowired
    public SnowboardBootService(ItemRepository<SnowboardBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SnowboardBootMapper snowboardBootMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardBootMapper = snowboardBootMapper;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        SnowboardBoot snowboardBoot = snowboardBootMapper.snowboardBootRequestToSnowboardBoot((SnowboardBootRequest) itemRequest);
        snowboardBoot.setImage(image);
        return snowboardBoot;
    }
}
