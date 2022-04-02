package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.PantsRequest;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.mapper.PantsMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PantsService extends BaseItemServiceImpl<Pants> {
    private final PantsMapper pantsMapper;

    @Autowired
    public PantsService(ItemRepository<Pants> itemRepository, EquipmentSizeRepository equipmentSizeRepository, PantsMapper pantsMapper) {
        super(itemRepository, equipmentSizeRepository);
        this.pantsMapper = pantsMapper;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Pants pants = pantsMapper.pantsRequestToPants((PantsRequest) itemRequest);
        pants.setImage(image);
        return pants;
    }
}
