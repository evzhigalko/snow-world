package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.CapRequest;
import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.mapper.CapMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.CapRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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
