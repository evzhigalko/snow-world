package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.MittenRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.mapper.item.MittenMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.MittenRepository;
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
public class MittenService extends BaseItemServiceImpl<Mitten> {
    private final MittenMapper mittenMapper;
    private final MittenRepository mittenRepository;

    @Autowired
    public MittenService(ItemRepository<Mitten> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MittenMapper mittenMapper, MittenRepository mittenRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.mittenMapper = mittenMapper;
        this.mittenRepository = mittenRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Mitten mitten = mittenMapper.mittenRequestToMitten((MittenRequest) itemRequest);
        mitten.setImage(image);
        log.info("Got mitten from ItemRequest: " + mitten);
        return mitten;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Mitten> mittenPage = mittenRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = mittenPage.getTotalPages();
        log.info("Mitten has pages: " + totalPages);
        List<Mitten> mittenList = mittenPage.stream().collect(Collectors.toList());
        log.info("Got " + mittenList);
        return mittenMapper.mittenListToMittenResponseList(mittenList);
    }
}
