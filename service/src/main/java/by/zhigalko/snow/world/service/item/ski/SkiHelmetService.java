package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiHelmetRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.mapper.SkiHelmetMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiHelmetService extends BaseItemServiceImpl<SkiHelmet> {
    private final SkiHelmetMapper skiHelmetMapper;

    @Autowired
    public SkiHelmetService(ItemRepository<SkiHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiHelmetMapper skiHelmetMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.skiHelmetMapper = skiHelmetMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiHelmet skiHelmet = skiHelmetMapper.skiHelmetRequestToSkiHelmet((SkiHelmetRequest) itemRequest);
        skiHelmet.setImage(image);
        return skiHelmet;
    }
}
