package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.request.SnowboardBootRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.SnowboardBoot;
import by.zhigalko.snow.world.mapper.SnowboardBootMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import by.zhigalko.snow.world.repository.SnowBoardBootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
