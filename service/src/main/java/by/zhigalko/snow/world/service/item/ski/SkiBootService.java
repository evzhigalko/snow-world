package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiBootRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.mapper.SkiBootMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SkiBootRepository;
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
public class SkiBootService extends BaseItemServiceImpl<SkiBoot> {
    private final SkiBootMapper skiBootMapper;
    private final SkiBootRepository skiBootRepository;

    @Autowired
    public SkiBootService(ItemRepository<SkiBoot> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiBootMapper skiBootMapper, SkiBootRepository skiBootRepository) {
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
