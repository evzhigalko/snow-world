package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.SkiHelmetRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.SkiHelmet;
import by.zhigalko.snowworld.mapper.SkiHelmetMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.SkiHelmetRepository;
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
