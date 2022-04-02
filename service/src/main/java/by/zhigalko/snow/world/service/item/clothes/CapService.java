package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.CapRequest;
import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.mapper.CapMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CapService extends BaseItemServiceImpl<Cap> {
    private final CapMapper capMapper;

    public CapService(ItemRepository<Cap> itemRepository, EquipmentSizeRepository equipmentSizeRepository, CapMapper capMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.capMapper = capMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Cap cap = capMapper.capRequestToCap((CapRequest) itemRequest);
        cap.setImage(image);
        return cap;
    }
}
