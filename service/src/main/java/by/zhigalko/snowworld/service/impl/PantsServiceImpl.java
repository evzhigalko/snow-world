package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.PantsRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Pants;
import by.zhigalko.snowworld.mapper.PantsMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.PantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("pantsService")
public class PantsServiceImpl extends BaseItemServiceImpl<Pants> {
    private final PantsMapper pantsMapper;
    private final PantsRepository pantsRepository;

    @Autowired
    public PantsServiceImpl(ItemRepository<Pants> itemRepository, EquipmentSizeRepository equipmentSizeRepository, PantsMapper pantsMapper, PantsRepository pantsRepository) {
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
