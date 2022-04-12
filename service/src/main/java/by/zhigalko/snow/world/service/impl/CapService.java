package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.CapRequest;
import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.Cap;
import by.zhigalko.snow.world.mapper.CapMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.CapRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CapService extends BaseItemServiceImpl<Cap> {
    private final CapMapper capMapper;
    private final CapRepository capRepository;

    public CapService(ItemRepository<Cap> itemRepository, EquipmentSizeRepository equipmentSizeRepository, CapMapper capMapper, CapRepository capRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.capMapper = capMapper;
        this.capRepository = capRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Cap cap = capMapper.capRequestToCap((CapRequest) itemRequest);
        cap.setImage(image);
        log.info("Got cap from ItemRequest: " + cap);
        return cap;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Cap> capPage = capRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = capPage.getTotalPages();
        log.info("Cap has pages: " + totalPages);
        List<Cap> capList = capPage.stream().collect(Collectors.toList());
        log.info("Got " + capList);
        return capMapper.capListToCapResponseList(capList);
    }
}
