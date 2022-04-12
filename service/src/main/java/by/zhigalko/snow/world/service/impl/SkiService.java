package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.request.SkiRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.Ski;
import by.zhigalko.snow.world.mapper.SkiMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import by.zhigalko.snow.world.repository.SkiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Got ski from ItemRequest: " + ski);
        return ski;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Ski> skiPage = skiRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = skiPage.getTotalPages();
        log.info("Ski has pages: " + totalPages);
        List<Ski> skiList = skiPage.stream().collect(Collectors.toList());
        log.info("Got " + skiList);
        return skiMapper.skiListToSkiResponseList(skiList);
    }
}
