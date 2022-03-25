package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("cartService")
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean addToCart(UUID cartId, Item item) {
        Cart cart = cartRepository.getById(cartId);
        return cart.getItems().add(item);
    }

    @Override
    public boolean removeFromCart(UUID cartId, Item item) {
        Cart cart = cartRepository.getById(cartId);
        return cart.getItems().remove(item);
    }
}
