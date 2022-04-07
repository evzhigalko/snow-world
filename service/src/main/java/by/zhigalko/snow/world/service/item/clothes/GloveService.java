package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.GloveRequest;
import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Glove;
import by.zhigalko.snow.world.mapper.item.GloveMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.GloveRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
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
public class GloveService extends BaseItemServiceImpl<Glove> {
    private final GloveMapper gloveMapper;
    private final GloveRepository gloveRepository;

    @Autowired
    public GloveService(ItemRepository<Glove> itemRepository, EquipmentSizeRepository equipmentSizeRepository, GloveMapper gloveMapper, GloveRepository gloveRepository) {
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
