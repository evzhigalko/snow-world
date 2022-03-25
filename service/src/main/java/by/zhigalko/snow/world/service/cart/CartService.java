package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Item;
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
}
