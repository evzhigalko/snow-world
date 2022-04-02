package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiPoleRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.SkiPole;
import by.zhigalko.snow.world.mapper.SkiPoleMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkiPoleService extends BaseItemServiceImpl<SkiPole> {
    private final SkiPoleMapper skiPoleMapper;

    @Autowired
    public SkiPoleService(ItemRepository<SkiPole> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiPoleMapper skiPoleMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.skiPoleMapper = skiPoleMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiPole skiPole = skiPoleMapper.skiPoleRequestToSkiPole((SkiPoleRequest) itemRequest);
        skiPole.setImage(image);
        return skiPole;
    }
}
