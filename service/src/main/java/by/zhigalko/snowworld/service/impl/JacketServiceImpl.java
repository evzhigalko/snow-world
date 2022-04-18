package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.JacketRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Jacket;
import by.zhigalko.snowworld.mapper.JacketMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.JacketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("jacketService")
public class JacketServiceImpl extends BaseItemServiceImpl<Jacket> {
    private final JacketMapper jacketMapper;
    private final JacketRepository jacketRepository;

    @Autowired
    public JacketServiceImpl(ItemRepository<Jacket> itemRepository, EquipmentSizeRepository equipmentSizeRepository, JacketMapper jacketMapper, JacketRepository jacketRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.jacketMapper = jacketMapper;
        this.jacketRepository = jacketRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Jacket jacket = jacketMapper.jacketRequestToJacket((JacketRequest) itemRequest);
        jacket.setImage(image);
        log.info("Got jacket from ItemRequest: " + jacket);
        return jacket;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Jacket> jacketPage = jacketRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = jacketPage.getTotalPages();
        log.info("Jacket has pages: " + totalPages);
        List<Jacket> jacketList = jacketPage.stream().collect(Collectors.toList());
        log.info("Got " + jacketList);
        return jacketMapper.jacketListToJacketResponseList(jacketList);
    }
}
