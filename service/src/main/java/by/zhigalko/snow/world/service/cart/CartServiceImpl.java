package by.zhigalko.snow.world.service.cart;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.mapper.CartMapper;
import by.zhigalko.snow.world.mapper.ItemMapper;
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
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemRepository<T> itemRepository, ServiceEquipmentFactory serviceEquipmentFactory, ItemMapper itemMapper, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.serviceEquipmentFactory = serviceEquipmentFactory;
        this.itemMapper = itemMapper;
        this.cartMapper = cartMapper;
    }

    @Transactional
    @Override
    public CartDto addToCart(BaseItemService service, CartDto cartDto, UUID itemId) {
        Cart cart = cartMapper.cartDtoToCart(cartDto);
        Item item = service.findById(itemId);
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
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.cartToCartDto(savedCart);
    }

    @Transactional
    @Override
    public CartDto removeFromCart(CartDto cartDto, UUID id, Set<ItemResponse> cartItems) {
        Cart cart = cartMapper.cartDtoToCart(cartDto);
        Product product = cartItems.stream()
                .filter(item -> id.equals(item.getId()))
                .map(ItemResponse::getProductName)
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
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.cartToCartDto(savedCart);
    }

    @Override
    public CartDto findCartById(UUID id) {
        Cart cart = cartRepository.findCartById(id);
        return cartMapper.cartToCartDto(cart);
    }

    @Transactional
    @Override
    public Set<ItemResponse> getItems(UUID cartId) {
        Set<Item> items = cartRepository.getItems(cartId);
        return itemMapper.itemSetToItemResponseSet(items);
    }
}
