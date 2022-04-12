package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.request.SkiPoleRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.SkiPole;
import by.zhigalko.snow.world.mapper.SkiPoleMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import by.zhigalko.snow.world.repository.SkiPoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SkiPoleService extends BaseItemServiceImpl<SkiPole> {
    private final SkiPoleMapper skiPoleMapper;
    private final SkiPoleRepository skiPoleRepository;

    @Autowired
    public SkiPoleService(ItemRepository<SkiPole> itemRepository, EquipmentSizeRepository equipmentSizeRepository, SkiPoleMapper skiPoleMapper, SkiPoleRepository skiPoleRepository) {
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
