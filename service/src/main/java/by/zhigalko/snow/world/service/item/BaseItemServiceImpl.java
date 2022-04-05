package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@Getter
public abstract class BaseItemServiceImpl<T extends Item> implements BaseItemService<T> {
    private final ItemRepository<T> itemRepository;
    private final EquipmentSizeRepository equipmentSizeRepository;
    public static int totalPages;

    @Autowired
    public BaseItemServiceImpl(ItemRepository<T> itemRepository, EquipmentSizeRepository equipmentSizeRepository) {
        this.itemRepository = itemRepository;
        this.equipmentSizeRepository = equipmentSizeRepository;
    }

    @Override
    public T save(T item) {
        T saved = itemRepository.save(item);
        log.info("Saved entity: " + saved);
        return saved;
    }

    @Transactional
    @Override
    public T findById(UUID id) {
        T item = itemRepository.findById(id).orElseThrow(NoResultException::new);
        log.info("Found entity " + item);
        return item;
    }

    @Override
    public void delete(T item) {
        log.info("Deleted entity " + item);
        itemRepository.deleteById(item.getId());
    }

    @Override
    public List<EquipmentSize> findAllByProductGroup(ProductGroup productGroup) {
        List<EquipmentSize> sizeList = equipmentSizeRepository.findAllByProductGroupOrderByEquipmentSizeId(productGroup);
        log.info("Got " + sizeList);
        return sizeList;
    }
}
