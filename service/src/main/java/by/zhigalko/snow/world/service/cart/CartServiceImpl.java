package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.repository.CartRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.UUID;

@Service("cartService")
@Transactional
public class CartServiceImpl<T extends Item> implements CartService {
    private final CartRepository cartRepository;
    private final ItemRepository<T> itemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemRepository<T> itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Cart addToCart(BaseItemServiceImpl service, Cart cart, Item item) {
        Item savedItem = service.save(item);
        Set<Item> items = cartRepository.getItems(cart.getId());
        items.add(savedItem);
        cart.setItems(items);
        Set<Cart> carts = itemRepository.getCarts(cart.getId());
        carts.add(cart);
        return cartRepository.save(cart);
    }

    @Override
    public boolean removeFromCart(Cart cart, Item item) {
        return cart.getItems().remove(item);
    }

    @Override
    public Cart findCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Transactional
    @Override
    public Set<Item> getItems(UUID cartId) {
        return cartRepository.getItems(cartId);
    }
}
