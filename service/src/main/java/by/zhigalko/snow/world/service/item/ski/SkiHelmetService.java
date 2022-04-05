package by.zhigalko.snow.world.service.item.ski;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.SkiHelmetRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.mapper.SkiHelmetMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.SkiHelmetRepository;
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
public class SkiHelmetService extends BaseItemServiceImpl<SkiHelmet> {
    private final SkiHelmetMapper skiHelmetMapper;
    private final SkiHelmetRepository skiHelmetRepository;

    @Autowired
    public SkiHelmetService(ItemRepository<SkiHelmet> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiHelmetMapper skiHelmetMapper, SkiHelmetRepository skiHelmetRepository) {
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
