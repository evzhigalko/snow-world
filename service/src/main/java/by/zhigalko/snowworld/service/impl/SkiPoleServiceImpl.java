package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.SkiPoleRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.SkiPole;
import by.zhigalko.snowworld.mapper.SkiPoleMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.SkiPoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("skiPoleService")
public class SkiPoleServiceImpl extends BaseItemServiceImpl<SkiPole> {
    private final SkiPoleMapper skiPoleMapper;
    private final SkiPoleRepository skiPoleRepository;

    @Autowired
    public SkiPoleServiceImpl(ItemRepository<SkiPole> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiPoleMapper skiPoleMapper, SkiPoleRepository skiPoleRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.skiPoleMapper = skiPoleMapper;
        this.skiPoleRepository = skiPoleRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        SkiPole skiPole = skiPoleMapper.skiPoleRequestToSkiPole((SkiPoleRequest) itemRequest);
        skiPole.setImage(image);
        log.info("Got skiPole from ItemRequest: " + skiPole);
        return skiPole;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<SkiPole> skiPolePage = skiPoleRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = skiPolePage.getTotalPages();
        log.info("SkiPole has pages: " + totalPages);
        List<SkiPole> skiPoleList = skiPolePage.stream().collect(Collectors.toList());
        log.info("Got " + skiPoleList);
        return skiPoleMapper.skiPoleListToSkiPoleResponseList(skiPoleList);
    }
}
