package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.mapper.item.SnowboardMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SnowboardRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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
