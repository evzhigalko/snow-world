package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.config.ApplicationConfig;
import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Order;
import by.zhigalko.snowworld.service.CartService;
import by.zhigalko.snowworld.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfig.class, BCryptPasswordEncoder.class})
@Transactional
class OrderMapperTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void orderRequestToOrderTest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCartId("1221f2f3-f37e-445e-ab76-30adef9be35e");
        orderRequest.setStartReservationDate("2022-04-15");
        orderRequest.setReservationDayNumber("3");
        orderRequest.setTotalSum("26");
        orderRequest.setItems(String.valueOf(cartService.findCartById(UUID.fromString(orderRequest.getCartId())).getItems()));

        Order order = orderMapper.orderRequestToOrder(orderRequest);

        assertNotNull(order);
        assertEquals(UUID.fromString(orderRequest.getCartId()), order.getUser().getId());
        assertEquals(Double.parseDouble(orderRequest.getTotalSum()), order.getTotalSum());
        System.out.println(orderRequest);
        System.out.println(order);
    }

    @Test
    void orderToOrderResponseTest() {
        Order order = new Order();
        UUID id = UUID.fromString("1221f2f3-f37e-445e-ab76-30adef9be35e");
        order.setUser(userService.findById(id));
        CartDto cart = cartService.findCartById(id);
        order.setItems(cart.getItems());
        order.setTotalSum(cart.getTotalSum());
        order.setReservationDayNumber(cart.getReservationDayNumber());
        order.setStartReservationDate(cart.getStartReservationDate());
        order.setId(UUID.randomUUID());

        OrderResponse orderResponse = orderMapper.orderToOrderResponse(order);

        assertNotNull(orderResponse);
        assertEquals(order.getItems().size(), orderResponse.getItems().size());
        assertEquals(order.getTotalSum(), orderResponse.getTotalSum());
        System.out.println(order);
        System.out.println(orderResponse);
    }

    @Test
    void orderListToOrderResponseList(){
        Order order = new Order();
        UUID id = UUID.fromString("1221f2f3-f37e-445e-ab76-30adef9be35e");
        order.setUser(userService.findById(id));
        CartDto cart = cartService.findCartById(id);
        order.setItems(cart.getItems());
        order.setTotalSum(cart.getTotalSum());
        order.setReservationDayNumber(cart.getReservationDayNumber());
        order.setStartReservationDate(cart.getStartReservationDate());
        order.setId(UUID.randomUUID());

        Order order2 = new Order();
        UUID id2 = UUID.fromString("1221f2f3-f37e-445e-ab76-30adef9be35e");
        order.setUser(userService.findById(id2));
        CartDto cart2 = cartService.findCartById(id2);
        order2.setItems(cart2.getItems());
        order2.setTotalSum(cart2.getTotalSum());
        order2.setReservationDayNumber(cart2.getReservationDayNumber());
        order2.setStartReservationDate(cart2.getStartReservationDate());
        order2.setId(UUID.randomUUID());

        List<Order> orderList = List.of(order, order2);

        List<OrderResponse> orderResponses = orderMapper.orderListToOrderResponseList(orderList);

        assertNotNull(orderResponses);
        assertEquals(orderList.size(), orderResponses.size());
        assertEquals(orderList.get(0).getTotalSum(), orderResponses.get(0).getTotalSum());
        assertEquals(orderList.get(1).getStartReservationDate(), orderResponses.get(1).getStartReservationDate());
        assertEquals(orderList.get(1).getItems(), orderResponses.get(1).getItems());
        orderList.forEach(System.out::println);
        orderResponses.forEach(System.out::println);
    }
}