package by.zhigalko.snow.world.service.item;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentAllSizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public abstract class BaseItemServiceImpl<T extends Item> implements BaseItemService<T>, BaseUpdateItemService<T>, EquipmentAllSizesService {
    private final BaseDaoItemImpl<T> dao;

    @Autowired
    public BaseItemServiceImpl(BaseDaoItemImpl<T> dao) {
        this.dao = dao;
    }

    @Override
    public boolean save(T item) {
        return dao.save(item);
    }

    @Override
    public List<T> findAll(int page, int pageSize) {
        return dao.findAll(page, pageSize);
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public T findById(UUID id) {
        return dao.findById(id);
    }

    @Override
    public void delete(T item) {
        dao.delete(item);
    }

    @Override
    public T updateAvailable(T entity, boolean isAvailable) {
        return dao.updateAvailable(entity, isAvailable);
    }

    @Override
    public T updateCost(T entity, double newCost) {
        return dao.updateCost(entity, newCost);
    }

    @Override
    public List<EquipmentSize> findAllSizes() {
        return dao.findAllSizes();
    }
}
