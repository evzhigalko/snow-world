package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.repository.EquipmentSizeRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

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
        return itemRepository.save(item);
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
