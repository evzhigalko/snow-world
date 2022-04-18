package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Cart;
import by.zhigalko.snowworld.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findCartById(UUID id);

    @Query("select c.items from Cart c where c.id=:cartId")
    Set<Item> getItems(@Param("cartId") UUID id);
}
