package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.mapper.SkiMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SkiRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkiService extends BaseItemServiceImpl<Ski> {
    private final SkiMapper skiMapper;
    private final SkiRepository skiRepository;

    @Autowired
    public SkiService(ItemRepository<Ski> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiMapper skiMapper, SkiRepository skiRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.skiMapper = skiMapper;
        this.skiRepository = skiRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Ski ski = skiMapper.skiRequestToSki((SkiRequest) itemRequest);
        ski.setImage(image);
        return ski;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Ski> skiPage = skiRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = skiPage.getTotalPages();
        List<Ski> skiList = skiPage.stream().collect(Collectors.toList());
        return skiMapper.skiListToSkiResponseList(skiList);
    }
}
