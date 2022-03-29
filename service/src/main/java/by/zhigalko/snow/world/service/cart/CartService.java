package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.service.item.BaseItemService;
import java.util.Set;
import java.util.UUID;

/**
 * Service for Cart
 */
public interface CartService {
    /**
     Add item to cart.
     * @param cart cart  {@link Cart} get from session
     * @param item adding item to cart {@link Item}
     */
    Cart addToCart(BaseItemService service, Cart cart, Item item);

    /**
     * Remove item from cart.
     * @param cart cart  {@link Cart} get from session
     * @param item removing item from cart
     * @return <ul>
     *     <li>{@code true} if removed</li>
     *     <li>{@code false} if didn't remove</li>
     * </ul>
     */
     boolean removeFromCart(Cart cart, Item item);

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
