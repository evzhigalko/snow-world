package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.MittenRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.mapper.MittenMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MittenService extends BaseItemServiceImpl<Mitten> {
    private final MittenMapper mittenMapper;

    @Autowired
    public MittenService(ItemRepository<Mitten> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MittenMapper mittenMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.mittenMapper = mittenMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Mitten mitten = mittenMapper.mittenRequestToMitten((MittenRequest) itemRequest);
        mitten.setImage(image);
        return mitten;
    }
}
