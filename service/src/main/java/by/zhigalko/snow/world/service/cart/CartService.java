package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.service.item.BaseItemService;
import java.util.Set;
import java.util.UUID;

/**
 * Service for Cart
 */
public interface CartService {
     /**
     * Add item to cart.
     * @param service {@link BaseItemService} is required for find and save new item
     * @param cart {@link Cart} is received from session
     * @param item {@link Item}  is received from service by id
     * @return cart {@link Cart}
     */
    Cart addToCart(BaseItemService service, Cart cart, Item item);

    /**
     * Remove item from cart.
     * @param cart {@link Cart} is received from session
     * @param id {@link UUID}  is received from query path
     * @param cartItems {@link Set<Item>} is received from session
     */
     Cart removeFromCart(Cart cart, UUID id, Set<Item> cartItems);

    /**
     * Find cart by id
     * @param id {@link UUID}
     * @return {@link Cart}
     */
     Cart findCartById(UUID id);

    /**
     * Get {@link Set<Item>}
     * @param cartId {@link UUID} cartId
     * @return {@link Set<Item>}
     */
     Set<Item> getItems(UUID cartId);
}
