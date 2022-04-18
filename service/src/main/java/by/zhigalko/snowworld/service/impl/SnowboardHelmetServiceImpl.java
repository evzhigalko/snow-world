package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.SnowboardHelmetRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.SnowboardHelmet;
import by.zhigalko.snowworld.mapper.SnowboardHelmetMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.SnowboardHelmetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("snowboardHelmetService")
public class SnowboardHelmetServiceImpl extends BaseItemServiceImpl<SnowboardHelmet> {
    private final SnowboardHelmetMapper snowboardHelmetMapper;
    private final SnowboardHelmetRepository snowboardHelmetRepository;

    @Autowired
    public SnowboardHelmetServiceImpl(ItemRepository<SnowboardHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository,
                                      SnowboardHelmetMapper snowboardHelmetMapper, SnowboardHelmetRepository snowboardHelmetRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardHelmetMapper = snowboardHelmetMapper;
        this.snowboardHelmetRepository = snowboardHelmetRepository;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        SnowboardHelmet snowboardHelmet = snowboardHelmetMapper.snowboardHelmetRequestToSnowboardHelmet((SnowboardHelmetRequest) itemRequest);
        snowboardHelmet.setImage(image);
        log.info("Got snowboardHelmet from ItemRequest: " + snowboardHelmet);
        return snowboardHelmet;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<SnowboardHelmet> snowboardHelmetPage = snowboardHelmetRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = snowboardHelmetPage.getTotalPages();
        log.info("SnowboardHelmet has pages: " + totalPages);
        List<SnowboardHelmet> snowboardHelmetList = snowboardHelmetPage.stream().collect(Collectors.toList());
        log.info("Got " + snowboardHelmetList);
        return snowboardHelmetMapper.snowboardHelmetListToSnowboardHelmetResponseList(snowboardHelmetList);
    }
}
