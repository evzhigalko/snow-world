package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.SkiRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Ski;
import by.zhigalko.snowworld.mapper.SkiMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.SkiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("skiService")
public class SkiServiceImpl extends BaseItemServiceImpl<Ski> {
    private final SkiMapper skiMapper;
    private final SkiRepository skiRepository;

    @Autowired
    public SkiServiceImpl(ItemRepository<Ski> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiMapper skiMapper, SkiRepository skiRepository) {
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
