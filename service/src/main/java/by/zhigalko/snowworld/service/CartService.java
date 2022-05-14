package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Cart;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.mapper.CartMapper;
import java.util.Set;
import java.util.UUID;

/**
 * Service for Cart
 */
public interface CartService {
     /**
     * Add item to cart.
     * @param cartDto {@link CartDto} is received from session
     * @param itemId {@link UUID}  is received from controller
     * @return cartDto {@link CartDto}
     */
    CartDto addToCart(CartDto cartDto, UUID itemId);

    /**
     * Remove item from cart.
     * @param cartDto {@link CartDto} is received from session
     * @param id {@link UUID}  is received from query path
     */
     CartDto removeFromCart(CartDto cartDto, UUID id);

    /**
     * Find cart by id
     * @param id {@link UUID}
     * @return {@link CartDto}
     */
     CartDto findCartById(UUID id);

    /**
     * Get {@link Set<ItemResponse>}
     * @param cartId {@link UUID} cartId
     * @return {@link Set<Item>}
     */
     Set<ItemResponse> getItemsFromCart(UUID cartId);

    /**
     * Save cart in database
     *
     * @param cartDto {@link CartDto} is received from controller,
     *                then using {@link CartMapper}
     *                save {@link Cart},
     * @return {@link CartDto} using {@link CartMapper} again.
     */
    CartDto save(CartDto cartDto);

    /**
     *  Clear items after successfully sending order
     * @param cartDto {@link CartDto} is received from session
     * @return {@link CartDto}
     */
    CartDto clearCart(CartDto cartDto);
}
