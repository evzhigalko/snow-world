package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.request.MaskRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.Mask;
import by.zhigalko.snowworld.mapper.MaskMapper;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.repository.MaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("maskService")
public class MaskServiceImpl extends BaseItemServiceImpl<Mask> {
    private final MaskMapper maskMapper;
    private final MaskRepository maskRepository;

    @Autowired
    public MaskServiceImpl(ItemRepository<Mask> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MaskMapper maskMapper, MaskRepository maskRepository) {
        super(itemRepository, equipmentSizeRepository);
        this.maskMapper = maskMapper;
        this.maskRepository = maskRepository;
    }

    @Override
    public Item getItem(ItemRequest itemRequest, Image image) {
        Mask mask = maskMapper.maskRequestToMask((MaskRequest) itemRequest);
        mask.setImage(image);
        log.info("Got mask from ItemRequest: " + mask);
        return mask;
    }

    @Override
    public List<? extends ItemResponse> findAll(int page, int pageSize) {
        Page<Mask> maskPage = maskRepository.findAll(PageRequest.of(page, pageSize));
        totalPages = maskPage.getTotalPages();
        log.info("Mask has pages: " + totalPages);
        List<Mask> maskList = maskPage.stream().collect(Collectors.toList());
        log.info("Got " + maskList);
        return maskMapper.maskListToMaskResponseList(maskList);
    }
}
