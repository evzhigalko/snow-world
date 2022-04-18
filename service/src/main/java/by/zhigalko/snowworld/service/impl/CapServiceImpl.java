package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.CapRequest;
import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Cap;
import by.zhigalko.snowworld.mapper.CapMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.CapRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("capService")
public class CapServiceImpl extends BaseItemServiceImpl<Cap> {
    private final CapMapper capMapper;
    private final CapRepository capRepository;

    public CapServiceImpl(ItemRepository<Cap> itemRepository, EquipmentSizeRepository equipmentSizeRepository, CapMapper capMapper, CapRepository capRepository) {
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
