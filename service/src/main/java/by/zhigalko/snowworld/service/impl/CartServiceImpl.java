package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Cart;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.mapper.CartMapper;
import by.zhigalko.snowworld.mapper.ItemMapper;
import by.zhigalko.snowworld.repository.CartRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.service.CartService;
import by.zhigalko.snowworld.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;
    private final ItemService itemService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemRepository itemRepository, ItemMapper itemMapper, CartMapper cartMapper, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.cartMapper = cartMapper;
        this.itemService = itemService;
    }

    @Transactional
    @Override
    public CartDto addToCart(CartDto cartDto, UUID itemId) {
        Item item = itemService.findById(itemId);
        Cart cart = cartMapper.cartDtoToCart(cartDto);
        Set<Item> items = cartRepository.getItems(cart.getId());
        items.add(item);
        log.info("Added to cart " + item);
        cart.setItems(items);
        cart.setStartReservationDate(LocalDate.now());
        cart.setReservationDayNumber(1);
        cart.setTotalSum(items.stream()
                .map(Item::getCost)
                .reduce(0.0, Double::sum));
        Set<Cart> carts = itemRepository.getCarts(item.getId());
        carts.add(cart);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.cartToCartDto(savedCart);
    }

    @Transactional
    @Override
    public CartDto removeFromCart(CartDto cartDto, UUID itemId) {
        Cart cart = cartMapper.cartDtoToCart(cartDto);
        Item item = itemService.findById(itemId);
        Set<Item> items = cartRepository.getItems(cart.getId());
        log.info("Removed to cart " + item);
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
        log.info("Found cart: " + cart);
        return cartMapper.cartToCartDto(cart);
    }

    @Transactional
    @Override
    public Set<ItemResponse> getItemsFromCart(UUID cartId) {
        Set<Item> items = cartRepository.getItems(cartId);
        return itemMapper.itemSetToItemResponseSet(items);
    }

    @Override
    public CartDto save(CartDto cartDto) {
        Cart cart = cartMapper.cartDtoToCart(cartDto);
        return cartMapper.cartToCartDto(cartRepository.save(cart));
    }

    @Transactional
    @Override
    public CartDto clearCart(CartDto cartDto) {
        Set<Item> items = cartDto.getItems();
        items.clear();
        cartDto.setItems(items);
        cartDto.setTotalSum(0.0);
        cartDto.setReservationDayNumber(0);
        cartDto.setStartReservationDate(null);
        return save(cartDto);
    }
}
