package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {
    private OrderRequest orderRequest;
    private OrderResponse orderResponse;
    private Order order;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderRequest = new OrderRequest();
        orderRequest.setCartId("1221f2f3-f37e-445e-ab76-30adef9be35e");
        orderRequest.setStartReservationDate("2022-04-15");
        orderRequest.setReservationDayNumber("3");
        orderRequest.setTotalSum("26");
        order = new Order();
        order.setId(UUID.randomUUID());
        order.setTotalSum(Double.parseDouble(orderRequest.getTotalSum()));
        order.setReservationDayNumber(Integer.parseInt(orderRequest.getReservationDayNumber()));
        order.setStartReservationDate(LocalDate.parse(orderRequest.getStartReservationDate()));
        orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setTotalSum(order.getTotalSum());
        orderResponse.setReservationDayNumber(order.getReservationDayNumber());
        orderResponse.setStartReservationDate(order.getStartReservationDate());
    }

    @Test
    void orderRequestToOrderTest() {
        doReturn(order).when(orderMapper).orderRequestToOrder(orderRequest);

        Order actualOrder = orderMapper.orderRequestToOrder(orderRequest);

        assertThat(actualOrder).isEqualTo(order);
        verify(orderMapper).orderRequestToOrder(orderRequest);
    }

    @Test
    void orderToOrderResponseTest() {
        doReturn(orderResponse).when(orderMapper).orderToOrderResponse(order);

        OrderResponse actualOrderResponse = orderMapper.orderToOrderResponse(order);

        assertThat(actualOrderResponse).isEqualTo(orderResponse);
        verify(orderMapper).orderToOrderResponse(order);
    }

    @Test
    void orderListToOrderResponseList(){
        List<Order> orderList = List.of(order);
        doReturn(List.of(orderResponse)).when(orderMapper).orderListToOrderResponseList(orderList);

        List<OrderResponse> actualOrderResponseList = orderMapper.orderListToOrderResponseList(orderList);

        assertThat(actualOrderResponseList).isEqualTo(List.of(orderResponse));
        verify(orderMapper).orderListToOrderResponseList(orderList);
    }
}
