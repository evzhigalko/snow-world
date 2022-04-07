package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardHelmetRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.mapper.item.SnowboardHelmetMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SnowboardHelmetRepository;
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
public class SnowboardHelmetService extends BaseItemServiceImpl<SnowboardHelmet> {
    private final SnowboardHelmetMapper snowboardHelmetMapper;
    private final SnowboardHelmetRepository snowboardHelmetRepository;

    @Autowired
    public SnowboardHelmetService(ItemRepository<SnowboardHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository,
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
