package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentAllSizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public abstract class BaseItemServiceImpl<T extends Item> implements BaseItemService<T>, BaseUpdateItemService<T>, EquipmentAllSizesService {
    private final ItemRepository<T> itemRepository;
    private final EquipmentSizeRepository equipmentSizeRepository;

    @Autowired
    public BaseItemServiceImpl(ItemRepository<T> itemRepository, EquipmentSizeRepository equipmentSizeRepository) {
        this.itemRepository = itemRepository;
        this.equipmentSizeRepository = equipmentSizeRepository;
    }

    @Override
    public T save(T item) {
        return itemRepository.save(item);
    }

    @Override
    public Page<T> findAll(int page, int pageSize) {
        return itemRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Transactional
    @Override
    public T findById(UUID id) {
        return itemRepository.findById(id).orElseThrow(NoResultException::new);
    }

    @Override
    public void delete(T item) {
        itemRepository.deleteById(item.getId());
    }

    @Override
    public List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup) {
        return equipmentSizeRepository.findAllByProductGroupOrderByEquipmentSizeId(productGroup);
    }
}
