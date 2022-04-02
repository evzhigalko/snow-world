package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.GloveRequest;
import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Glove;
import by.zhigalko.snow.world.mapper.GloveMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GloveService extends BaseItemServiceImpl<Glove> {
    private final GloveMapper gloveMapper;

    @Autowired
    public GloveService(ItemRepository<Glove> itemRepository, EquipmentSizeRepository equipmentSizeRepository, GloveMapper gloveMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.gloveMapper = gloveMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Glove glove = gloveMapper.gloveRequestToGlove((GloveRequest) itemRequest);
        glove.setImage(image);
        return glove;
    }
}
