package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.mapper.SkiMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiService extends BaseItemServiceImpl<Ski> {
    private final SkiMapper skiMapper;

    @Autowired
    public SkiService(ItemRepository<Ski> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiMapper skiMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.skiMapper = skiMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Ski ski = skiMapper.skiRequestToSki((SkiRequest) itemRequest);
        ski.setImage(image);
        return ski;
    }
}
