package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.Snowboard;
import by.zhigalko.snow.world.mapper.SnowboardMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import by.zhigalko.snow.world.repository.SnowboardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SnowboardService extends BaseItemServiceImpl<Snowboard> {
    private final SnowboardMapper snowboardMapper;
    private final SnowboardRepository snowboardRepository;

    @Autowired
    public SnowboardService(ItemRepository<Snowboard> itemRepository, EquipmentSizeRepository equipmentSizeRepository,
                            SnowboardMapper snowboardMapper, SnowboardRepository snowboardRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardMapper = snowboardMapper;
        this.snowboardRepository = snowboardRepository;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        Snowboard snowboard = snowboardMapper.snowboardRequestToSnowboard((SnowboardRequest) itemRequest);
        snowboard.setImage(image);
        log.info("Got snowboard from ItemRequest: " + snowboard);
        return snowboard;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Snowboard> snowboardPage = snowboardRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = snowboardPage.getTotalPages();
        log.info("Snowboard has pages: " + totalPages);
        List<Snowboard> snowboardList = snowboardPage.stream().collect(Collectors.toList());
        log.info("Got " + snowboardList);
        return snowboardMapper.snowboardListToSnowboardResponseList(snowboardList);
    }
}
