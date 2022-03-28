package by.zhigalko.snow.world.repository.item;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, UUID> {
    @Query("select i.carts from Item i where i.id=:itemId")
    Set<Cart> getCarts(@Param("itemId") UUID id);
}
