package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.request.GloveRequest;
import by.zhigalko.snow.world.dto.request.ItemRequest;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.Glove;
import by.zhigalko.snow.world.mapper.GloveMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.GloveRepository;
import by.zhigalko.snow.world.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("gloveService")
public class GloveServiceImpl extends BaseItemServiceImpl<Glove> {
    private final GloveMapper gloveMapper;
    private final GloveRepository gloveRepository;

    @Autowired
    public GloveServiceImpl(ItemRepository<Glove> itemRepository, EquipmentSizeRepository equipmentSizeRepository, GloveMapper gloveMapper, GloveRepository gloveRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.gloveMapper = gloveMapper;
        this.gloveRepository = gloveRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Glove glove = gloveMapper.gloveRequestToGlove((GloveRequest) itemRequest);
        glove.setImage(image);
        log.info("Got glove from ItemRequest: " + glove);
        return glove;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Glove> glovePage = gloveRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = glovePage.getTotalPages();
        log.info("Glove has pages: " + totalPages);
        List<Glove> gloveList = glovePage.stream().collect(Collectors.toList());
        log.info("Got " + gloveList);
        return gloveMapper.gloveListToGloveResponseList(gloveList);
    }
}
