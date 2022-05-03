package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Cart;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("select i.carts from Item i where i.id=:itemId")
    Set<Cart> getCarts(@Param("itemId") UUID id);

    Page<Item> findAllByProductName(Product product, Pageable pageable);
}
