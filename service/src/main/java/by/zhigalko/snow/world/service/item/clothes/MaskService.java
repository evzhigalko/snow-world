package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.MaskRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Mask;
import by.zhigalko.snow.world.mapper.MaskMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.MaskRepository;
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
public class MaskService extends BaseItemServiceImpl<Mask> {
    private final MaskMapper maskMapper;
    private final MaskRepository maskRepository;

    @Autowired
    public MaskService(ItemRepository<Mask> itemRepository, EquipmentSizeRepository equipmentSizeRepository, MaskMapper maskMapper, MaskRepository maskRepository) {
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
