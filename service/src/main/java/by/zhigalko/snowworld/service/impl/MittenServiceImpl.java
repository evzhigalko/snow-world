package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.MittenRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Mitten;
import by.zhigalko.snowworld.mapper.MittenMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.MittenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("mittenService")
public class MittenServiceImpl extends BaseItemServiceImpl<Mitten> {
    private final MittenMapper mittenMapper;
    private final MittenRepository mittenRepository;

    @Autowired
    public MittenServiceImpl(ItemRepository<Mitten> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MittenMapper mittenMapper, MittenRepository mittenRepository) {
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
