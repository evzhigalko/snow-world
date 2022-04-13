package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.request.SkiHelmetRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.SkiHelmet;
import by.zhigalko.snow.world.mapper.SkiHelmetMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import by.zhigalko.snow.world.repository.SkiHelmetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("skiHelmetService")
public class SkiHelmetServiceImpl extends BaseItemServiceImpl<SkiHelmet> {
    private final SkiHelmetMapper skiHelmetMapper;
    private final SkiHelmetRepository skiHelmetRepository;

    @Autowired
    public SkiHelmetServiceImpl(ItemRepository<SkiHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiHelmetMapper skiHelmetMapper, SkiHelmetRepository skiHelmetRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.skiHelmetMapper = skiHelmetMapper;
        this.skiHelmetRepository = skiHelmetRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiHelmet skiHelmet = skiHelmetMapper.skiHelmetRequestToSkiHelmet((SkiHelmetRequest) itemRequest);
        skiHelmet.setImage(image);
        log.info("Got skiHelmet from ItemRequest: " + skiHelmet);
        return skiHelmet;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<SkiHelmet> skiHelmetPage = skiHelmetRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = skiHelmetPage.getTotalPages();
        log.info("SkiHelmet has pages: " + totalPages);
        List<SkiHelmet> skiHelmetList = skiHelmetPage.stream().collect(Collectors.toList());
        log.info("Got " + skiHelmetList);
        return skiHelmetMapper.skiHelmetListToSkiHelmetResponseList(skiHelmetList);
    }
}
