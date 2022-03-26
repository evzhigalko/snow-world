package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import java.util.Set;
import java.util.UUID;

/**
 * Service for Cart
 */
public interface CartService {
    /**
     Add item to cart.
     * @param cartId cart id {@link UUID}
     * @param item adding item to cart {@link Item}
     * @return
     */
     boolean addToCart(UUID cartId, Item item);

    /**
     * Remove item from cart.
     * @param cartId cart id {@link UUID}
     * @param item removing item from cart
     * @return <ul>
     *     <li>{@code true} if removed</li>
     *     <li>{@code false} if didn't remove</li>
     * </ul>
     */
     boolean removeFromCart(UUID cartId, Item item);

    /**
     * Find cart by user
     * @param user {@link User}
     * @return {@link Cart}
     */
     Cart findCartByUser(User user);

    /**
     * Get {@link Set<Item>}
     * @param cartId {@link UUID} cartId
     * @return {@link Set<Item>}
     */
     Set<Item> getItems(UUID cartId);
}
