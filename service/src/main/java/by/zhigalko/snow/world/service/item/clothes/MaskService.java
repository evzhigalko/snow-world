package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.MaskRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Mask;
import by.zhigalko.snow.world.mapper.MaskMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaskService extends BaseItemServiceImpl<Mask> {
    private final MaskMapper maskMapper;

    @Autowired
    public MaskService(ItemRepository<Mask> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MaskMapper maskMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.maskMapper = maskMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Mask mask = maskMapper.maskRequestToMask((MaskRequest) itemRequest);
        mask.setImage(image);
        return mask;
    }
}
