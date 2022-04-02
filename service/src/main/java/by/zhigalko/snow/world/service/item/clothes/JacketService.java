package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.JacketRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.mapper.JacketMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JacketService extends BaseItemServiceImpl<Jacket> {
    private final JacketMapper jacketMapper;

    @Autowired
    public JacketService(ItemRepository<Jacket> itemRepository, EquipmentSizeRepository equipmentSizeRepository, JacketMapper jacketMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.jacketMapper = jacketMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Jacket jacket = jacketMapper.jacketRequestToJacket((JacketRequest) itemRequest);
        jacket.setImage(image);
        return jacket;
    }
}
