package by.zhigalko.snow.world.service.item.snowboard;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SnowboardBootRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.mapper.item.SnowboardBootMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SnowBoardBootRepository;
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
public class SnowboardBootService extends BaseItemServiceImpl<SnowboardBoot> {
     private final SnowboardBootMapper snowboardBootMapper;
     private final SnowBoardBootRepository snowBoardBootRepository;

    @Autowired
    public SnowboardBootService(ItemRepository<SnowboardBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository,
                                SnowboardBootMapper snowboardBootMapper, SnowBoardBootRepository snowBoardBootRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.snowboardBootMapper = snowboardBootMapper;
        this.snowBoardBootRepository = snowBoardBootRepository;
    }

    public Item getItem(ItemRequest itemRequest, Image image) {
        SnowboardBoot snowboardBoot = snowboardBootMapper.snowboardBootRequestToSnowboardBoot((SnowboardBootRequest) itemRequest);
        snowboardBoot.setImage(image);
        log.info("Got snowboardBoot from ItemRequest: " + snowboardBoot);
        return snowboardBoot;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<SnowboardBoot> snowboardBootPage = snowBoardBootRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = snowboardBootPage.getTotalPages();
        log.info("SnowboardBoot has pages: " + totalPages);
        List<SnowboardBoot> snowboardBootList = snowboardBootPage.stream().collect(Collectors.toList());
        log.info("Got " + snowboardBootList);
        return snowboardBootMapper.snowboardBootListToSnowboardBootResponseList(snowboardBootList);
    }
}
