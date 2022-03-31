package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.repository.CartRepository;
import by.zhigalko.snow.world.repository.item.ItemRepository;
import by.zhigalko.snow.world.service.item.BaseItemService;
import by.zhigalko.snow.world.service.item.ServiceEquipmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service("cartService")
@Transactional
public class CartServiceImpl<T extends Item> implements CartService {
    private final CartRepository cartRepository;
    private final ItemRepository<T> itemRepository;
    private final ServiceEquipmentFactory serviceEquipmentFactory;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemRepository<T> itemRepository, ServiceEquipmentFactory serviceEquipmentFactory) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.serviceEquipmentFactory = serviceEquipmentFactory;
    }

    @Transactional
    @Override
    public Cart addToCart(BaseItemService service, Cart cart, Item item) {
        Item savedItem = service.save(item);
        Set<Item> items = cartRepository.getItems(cart.getId());
        items.add(savedItem);
        cart.setItems(items);
        cart.setStartReservationDate(LocalDate.now());
        cart.setReservationDayNumber(1);
        cart.setTotalSum(items.stream()
                .map(Item::getCost)
                .reduce(0.0, Double::sum));
        Set<Cart> carts = itemRepository.getCarts(savedItem.getId());
        carts.add(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Cart removeFromCart(Cart cart, UUID id, Set<Item> cartItems) {
        Product product = cartItems.stream()
                .filter(item -> id.equals(item.getId()))
                .map(Item::getProductName)
                .findAny()
                .orElseThrow();
        BaseItemService<? extends Item> service =  serviceEquipmentFactory.getService(product);
        Item item = service.findById(id);
        Set<Item> items = cartRepository.getItems(cart.getId());
        items.remove(item);
        cart.setItems(items);
        cart.setTotalSum(cart.getTotalSum() - item.getCost());
        if (items.size() == 0) {
            cart.setReservationDayNumber(0);
            cart.setStartReservationDate(null);
        }
        Set<Cart> carts = itemRepository.getCarts(item.getId());
        carts.remove(cart);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartById(UUID id) {
        return cartRepository.findCartById(id);
    }

    @Transactional
    @Override
    public Set<Item> getItems(UUID cartId) {
        return cartRepository.getItems(cartId);
    }
}
