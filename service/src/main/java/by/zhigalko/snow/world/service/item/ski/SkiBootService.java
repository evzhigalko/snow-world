package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiBootRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.mapper.SkiBootMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiBootService extends BaseItemServiceImpl<SkiBoot> {
    private final SkiBootMapper skiBootMapper;

    @Autowired
    public SkiBootService(ItemRepository<SkiBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiBootMapper skiBootMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.skiBootMapper = skiBootMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiBoot skiBoot = skiBootMapper.skiBootRequestToSkiBoot((SkiBootRequest) itemRequest);
        skiBoot.setImage(image);
        return skiBoot;
    }
}
