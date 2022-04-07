package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.PantsRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.mapper.item.PantsMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.PantsRepository;
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
public class PantsService extends BaseItemServiceImpl<Pants> {
    private final PantsMapper pantsMapper;
    private final PantsRepository pantsRepository;

    @Autowired
    public PantsService(ItemRepository<Pants> itemRepository, EquipmentSizeRepository equipmentSizeRepository, PantsMapper pantsMapper, PantsRepository pantsRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.pantsMapper = pantsMapper;
        this.pantsRepository = pantsRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Pants pants = pantsMapper.pantsRequestToPants((PantsRequest) itemRequest);
        pants.setImage(image);
        log.info("Got pants from ItemRequest: " + pants);
        return pants;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Pants> pantsPage = pantsRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = pantsPage.getTotalPages();
        log.info("Pants has pages: " + totalPages);
        List<Pants> pantsList = pantsPage.stream().collect(Collectors.toList());
        log.info("Got " + pantsList);
        return pantsMapper.pantsListToPantsResponseList(pantsList);
    }
}
