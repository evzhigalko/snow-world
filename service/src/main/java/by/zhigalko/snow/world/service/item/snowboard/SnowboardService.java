package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.mapper.SnowboardMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowboardService extends BaseItemServiceImpl<Snowboard> {
    private final SnowboardMapper snowboardMapper;

    @Autowired
    public SnowboardService(ItemRepository<Snowboard> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SnowboardMapper snowboardMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardMapper = snowboardMapper;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        Snowboard snowboard = snowboardMapper.snowboardRequestToSnowboard((SnowboardRequest) itemRequest);
        snowboard.setImage(image);
        return snowboard;
    }
}
