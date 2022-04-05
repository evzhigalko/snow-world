package by.zhigalko.snow.world.service.item.clothes;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.request.JacketRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.mapper.JacketMapper;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.repository.item.JacketRepository;
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
public class JacketService extends BaseItemServiceImpl<Jacket> {
    private final JacketMapper jacketMapper;
    private final JacketRepository jacketRepository;

    @Autowired
    public JacketService(ItemRepository<Jacket> itemRepository, EquipmentSizeRepository equipmentSizeRepository, JacketMapper jacketMapper, JacketRepository jacketRepository) {
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
