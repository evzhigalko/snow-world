package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Cart;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.mapper.CartMapper;
import by.zhigalko.snowworld.mapper.ItemMapper;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.repository.CartRepository;
import by.zhigalko.snowworld.repository.ItemRepository;
import by.zhigalko.snowworld.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    private CartDto cartDto;
    private Cart cart;
    private Cart cart2;
    private Item item;
    private Set<Item> items;
    private Set<Cart> carts;
    private CartDto cartDto2;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        cartDto = new CartDto();
        cartDto.setId(UUID.randomUUID());
        cartDto.setTotalSum(15d);
        cartDto.setReservationDayNumber(5);
        cartDto.setStartReservationDate(LocalDate.of(2022, Month.MAY, 13));
        cartDto2 = new CartDto();
        cartDto2.setId(UUID.randomUUID());
        cartDto2.setTotalSum(15d);
        cartDto2.setReservationDayNumber(5);
        cartDto2.setStartReservationDate(LocalDate.of(2022, Month.MAY, 13));
        cartDto2.setItems(new HashSet<Item>());
        cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setTotalSum(cartDto.getTotalSum());
        cart.setReservationDayNumber(cartDto.getReservationDayNumber());
        cart.setStartReservationDate(cartDto.getStartReservationDate());
        cart2 = new Cart();
        cart2.setId(cartDto.getId());
        cart2.setTotalSum(cartDto.getTotalSum());
        cart2.setReservationDayNumber(cartDto.getReservationDayNumber());
        cart2.setStartReservationDate(cartDto.getStartReservationDate());
        item = new Item();
        item.setId(UUID.randomUUID());
        item.setCost(15d);
        item.setAvailableToRental(true);
        item.setProductName(Product.SNOWBOARD_BOOT);
        items = new HashSet<>();
        items.add(item);
        cart.setItems(items);
        cartDto.setItems(items);
        carts = new HashSet<>();
        carts.add(cart);
    }

    @Test
    void addToCartIfCartAndItemFoundTest() {
        doReturn(cart).when(cartMapper).cartDtoToCart(cartDto);
        doReturn(item).when(itemService).findById(item.getId());
        doReturn(new HashSet<Item>()).when(cartRepository).getItems(cart.getId());
        doReturn(new HashSet<Cart>()).when(itemRepository).getCarts(item.getId());
        doReturn(cart).when(cartRepository).save(cart);
        doReturn(cartDto).when(cartMapper).cartToCartDto(cart);

        CartDto actualCartDto = cartService.addToCart(cartDto, item.getId());

        assertThat(actualCartDto).isEqualTo(cartDto);

        verify(cartMapper).cartDtoToCart(cartDto);
        verify(itemService).findById(item.getId());
        verify(cartRepository).getItems(cart.getId());
        verify(itemRepository).getCarts(item.getId());
        verify(cartRepository).save(cart);
        verify(cartMapper).cartToCartDto(cart);
    }

    @Test
    void addToCartIfCartDtoIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> cartService.addToCart(null, item.getId()));
    }

    @Test
    void addToCartIfItemIdIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> cartService.addToCart(cartDto, null));
    }

    @Test
    void addToCartIfItemNotFoundTest() {
        UUID id = UUID.randomUUID();

        doThrow(NullPointerException.class).when(itemService).findById(id);

        assertThatNullPointerException().isThrownBy(() -> cartService.addToCart(cartDto, id));
    }

    @Test
    void removeFromCartIfCartAndItemFoundTest() {
        doReturn(cart).when(cartMapper).cartDtoToCart(cartDto);
        doReturn(item).when(itemService).findById(item.getId());
        doReturn(items).when(cartRepository).getItems(cart.getId());
        doReturn(carts).when(itemRepository).getCarts(item.getId());
        doReturn(cart2).when(cartRepository).save(cart);
        doReturn(cartDto2).when(cartMapper).cartToCartDto(cart2);

        CartDto actualCartDto = cartService.removeFromCart(cartDto, item.getId());

        assertThat(actualCartDto.getItems().size()).isEqualTo(cartDto2.getItems().size());

        verify(cartMapper).cartDtoToCart(cartDto);
        verify(itemService).findById(item.getId());
        verify(cartRepository).getItems(cart.getId());
        verify(itemRepository).getCarts(item.getId());
        verify(cartRepository).save(cart);
        verify(cartMapper).cartToCartDto(cart);
    }

    @Test
    void removeFromCartIfCartDtoIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> cartService.removeFromCart(null, item.getId()));
    }

    @Test
    void removeFromIfItemIdIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> cartService.removeFromCart(cartDto, null));
    }

    @Test
    void removeFromCartIfItemNotFoundTest() {
        UUID id = UUID.randomUUID();

        doThrow(NullPointerException.class).when(itemService).findById(id);

        assertThatNullPointerException().isThrownBy(() -> cartService.removeFromCart(cartDto, id));
    }

    @Test
    void findCartByIdIfIdIsNotNullTest() {
        doReturn(cart).when(cartRepository).findCartById(item.getId());
        doReturn(cartDto).when(cartMapper).cartToCartDto(cart);

        CartDto actualCartDto = cartService.findCartById(item.getId());

        assertThat(actualCartDto).isEqualTo(cartDto);

        verify(cartRepository).findCartById(item.getId());
        verify(cartMapper).cartToCartDto(cart);
    }

    @Test
    void findCartByIdIfIdIsNullTest() {
        doThrow(NullPointerException.class).when(cartRepository).findCartById(null);

        assertThatNullPointerException().isThrownBy(() -> cartService.findCartById(null));
    }

    @Test
    void getItemsFromCartIfCartIdIsNotNullTest() {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setCost(String.valueOf(item.getCost()));
        itemResponse.setProductName(item.getProductName());
        itemResponse.setAvailableToRental(String.valueOf(true));
        Set<ItemResponse> itemResponseSet = Set.of(itemResponse);
        doReturn(Set.of(item)).when(cartRepository).getItems(cart.getId());
        doReturn(itemResponseSet).when(itemMapper).itemSetToItemResponseSet(Set.of(item));

        Set<ItemResponse> actualItemResponseSet = cartService.getItemsFromCart(cart.getId());

        assertThat(actualItemResponseSet).isEqualTo(itemResponseSet);

        verify(cartRepository).getItems(cart.getId());
        verify(itemMapper).itemSetToItemResponseSet(Set.of(item));
    }

    @Test
    void getItemsFromCartIfCartIdIsNullTest() {
        doThrow(NullPointerException.class).when(cartRepository).getItems(null);

        assertThatNullPointerException().isThrownBy(() -> cartService.getItemsFromCart(null));
    }

    @Test
    void saveIfCartDtoIsNotNullTest() {
        doReturn(cart).when(cartMapper).cartDtoToCart(cartDto);
        doReturn(cart).when(cartRepository).save(cart);
        doReturn(cartDto).when(cartMapper).cartToCartDto(cart);

        CartDto actualCartDto = cartService.save(cartDto);

        assertThat(actualCartDto).isEqualTo(cartDto);

        verify(cartMapper).cartDtoToCart(cartDto);
        verify(cartRepository).save(cart);
        verify(cartMapper).cartToCartDto(cart);
    }

    @Test
    void saveIfCarDtoIsNullTest() {
        doThrow(NullPointerException.class).when(cartMapper).cartDtoToCart(null);

        assertThatNullPointerException().isThrownBy(() -> cartService.save(null));
    }

    @Test
    void clearCartIfCartDtoIsNotNullTest() {
        doReturn(cart).when(cartMapper).cartDtoToCart(cartDto);
        doReturn(cart).when(cartRepository).save(cart);
        doReturn(cartDto).when(cartMapper).cartToCartDto(cart);

        CartDto actualCartDto = cartService.clearCart(cartDto);

        assertThat(actualCartDto).isEqualTo(cartDto);

        verify(cartMapper).cartDtoToCart(cartDto);
        verify(cartRepository).save(cart);
        verify(cartMapper).cartToCartDto(cart);
    }

    @Test
    void clearCartIfCartDtoIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> cartService.clearCart(null));
    }
}
