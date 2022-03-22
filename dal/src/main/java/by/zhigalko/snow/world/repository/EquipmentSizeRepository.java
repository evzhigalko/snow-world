package by.zhigalko.snow.world.repository;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentSizeRepository extends JpaRepository<EquipmentSize, String> {
    List<EquipmentSize> findAllByProductGroupOrderByEquipmentSizeId(ProductGroup productGroup);
}
