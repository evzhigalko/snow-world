package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.SkiBootRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.SkiBoot;
import by.zhigalko.snowworld.mapper.SkiBootMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.SkiBootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("skiBootService")
public class SkiBootServiceImpl extends BaseItemServiceImpl<SkiBoot> {
    private final SkiBootMapper skiBootMapper;
    private final SkiBootRepository skiBootRepository;

    @Autowired
    public SkiBootServiceImpl(ItemRepository<SkiBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiBootMapper skiBootMapper, SkiBootRepository skiBootRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.skiBootMapper = skiBootMapper;
        this.skiBootRepository = skiBootRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiBoot skiBoot = skiBootMapper.skiBootRequestToSkiBoot((SkiBootRequest) itemRequest);
        skiBoot.setImage(image);
        log.info("Got skiBoot from ItemRequest: " + skiBoot);
        return skiBoot;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<SkiBoot> skiBootPage = skiBootRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = skiBootPage.getTotalPages();
        log.info("SkiBoot has pages: " + totalPages);
        List<SkiBoot> skiBootList = skiBootPage.stream().collect(Collectors.toList());
        log.info("Got " + skiBootList);
        return skiBootMapper.skiBootListToSkiBootResponseList(skiBootList);
    }
}
