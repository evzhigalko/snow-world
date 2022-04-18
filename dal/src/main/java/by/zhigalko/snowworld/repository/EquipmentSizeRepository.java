package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.model.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentSizeRepository extends JpaRepository<EquipmentSize, String> {
    List<EquipmentSize> findAllByProductGroupOrderByEquipmentSizeId(ProductGroup productGroup);
}
