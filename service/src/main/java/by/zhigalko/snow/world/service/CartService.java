package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.mapper.CartMapper;

import java.util.Set;
import java.util.UUID;

/**
 * Service for Cart
 */
public interface CartService {
     /**
     * Add item to cart.
     * @param service {@link BaseItemService} is required for find and save new item
     * @param cartDto {@link CartDto} is received from session
     * @param itemId {@link UUID}  is received from controller
     * @return cartDto {@link CartDto}
     */
    CartDto addToCart(BaseItemService service, CartDto cartDto, UUID itemId);

    /**
     * Remove item from cart.
     * @param cartDto {@link CartDto} is received from session
     * @param id {@link UUID}  is received from query path
     * @param cartItems {@link Set<ItemResponse>} is received from session
     */
     CartDto removeFromCart(CartDto cartDto, UUID id, Set<ItemResponse> cartItems);

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
     Set<ItemResponse> getItems(UUID cartId);

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
    CartDto clearItems(CartDto cartDto);
}
