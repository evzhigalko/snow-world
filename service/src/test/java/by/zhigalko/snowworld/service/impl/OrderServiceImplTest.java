package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.OrderDetailsDto;
import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Order;
import by.zhigalko.snowworld.entity.OrderDetails;
import by.zhigalko.snowworld.entity.User;
import by.zhigalko.snowworld.mapper.OrderMapper;
import by.zhigalko.snowworld.repository.OrderRepository;
import by.zhigalko.snowworld.service.OrderDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private User user;
    private Order order;
    private OrderResponse orderResponse;
    private OrderRequest orderRequest;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        orderRequest = new OrderRequest();
        orderRequest.setTotalSum(String.valueOf(777d));
        orderRequest.setCartId(String.valueOf(user.getId()));
        orderRequest.setReservationDayNumber("3");
        order = new Order();
        order.setUser(user);
        order.setReservationDayNumber(Integer.parseInt(orderRequest.getReservationDayNumber()));
        order.setTotalSum(Double.parseDouble(orderRequest.getTotalSum()));
        orderResponse = new OrderResponse();
        orderResponse.setReservationDayNumber(order.getReservationDayNumber());
        orderResponse.setTotalSum(order.getTotalSum());
    }

    @Test
    void findByIdIfIdIsNotNullTest() {
        doReturn(order).when(orderRepository).getById(order.getId());
        doReturn(orderResponse).when(orderMapper).orderToOrderResponse(order);

        OrderResponse actualOrderResponse = orderService.findById(order.getId());

        assertThat(actualOrderResponse).isEqualTo(orderResponse);
        verify(orderRepository).getById(order.getId());
        verify(orderMapper).orderToOrderResponse(order);
    }

    @Test
    void findByIdIfIdIsNullTest() {
        doThrow(NullPointerException.class).when(orderRepository).getById(null);

        assertThatNullPointerException().isThrownBy(() -> orderService.findById(null));

        verify(orderRepository).getById(null);
    }

    @Test
    void findByIdIfOderNotFoundTest() {
        UUID id = UUID.randomUUID();

        doThrow(EntityNotFoundException.class).when(orderRepository).getById(id);

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> orderService.findById(id));
    }

    @Test
    void findAllByUserIdIfUserIsNotNullTest() {
        doReturn(List.of(order)).when(orderRepository).findByUserIdOrderByStartReservationDate(user.getId());
        doReturn(List.of(orderResponse)).when(orderMapper).orderListToOrderResponseList(List.of(order));

        List<OrderResponse> actualOrderResponsesList = orderService.findAllByUserId(user.getId());

        assertThat(actualOrderResponsesList).isEqualTo(List.of(orderResponse));

        verify(orderRepository).findByUserIdOrderByStartReservationDate(user.getId());
        verify(orderMapper).orderListToOrderResponseList(List.of(order));
    }

    @Test
    void findAllByUserIdIfUserIdIsNullTest() {
        doThrow(NullPointerException.class).when(orderRepository).findByUserIdOrderByStartReservationDate(null);

        assertThatNullPointerException().isThrownBy(() -> orderService.findAllByUserId(null));

        verify(orderRepository).findByUserIdOrderByStartReservationDate(null);
    }

    @Test
    void findAllByUserIdIfOrderIsNotFoundTest() {
        UUID id = UUID.randomUUID();

        doThrow(EntityNotFoundException.class).when(orderRepository).findByUserIdOrderByStartReservationDate(id);

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> orderService.findAllByUserId(id));
    }

    @Test
    void saveIfOrderRequestIsNotNullTest() {
        OrderDetails orderDetails = new OrderDetails();
        doReturn(order).when(orderMapper).orderRequestToOrder(orderRequest);
        doReturn(orderDetails).when(orderDetailsService).save(orderDetails);
        doReturn(orderResponse).when(orderMapper).orderToOrderResponse(order);
        doReturn(order).when(orderRepository).save(order);

        OrderResponse orderResponse = orderService.save(orderRequest);

        assertThat(orderResponse).isEqualTo(this.orderResponse);

        verify(orderMapper).orderRequestToOrder(orderRequest);
        verify(orderDetailsService).save(orderDetails);
        verify(orderMapper).orderToOrderResponse(order);
        verify(orderRepository).save(order);
    }

    @Test
    void saveIfOrderRequestIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> orderService.save(null));
    }

    @Test
    void deleteIfOrderRequestIsNotNullTest() {
        doReturn(order).when(orderMapper).orderRequestToOrder(orderRequest);
        doNothing().when(orderRepository).delete(order);

        orderService.delete(orderRequest);

        verify(orderRepository).delete(order);
    }

    @Test
    void deleteIfOrderRequestIsNullTest() {
        doThrow(NullPointerException.class).when(orderMapper).orderRequestToOrder(null);

        assertThatNullPointerException().isThrownBy(() -> orderService.delete(null));
    }

    @Test
    void setOrderDetailsIfOrderDetailsDtoIsNotNullTest() {
        order.setId(UUID.randomUUID());
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setEmail("test@test.com");
        orderDetailsDto.setFirstname("Alex");
        orderDetailsDto.setLastname("Smith");
        orderDetailsDto.setOrderId(String.valueOf(order.getId()));
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(UUID.randomUUID());
        orderDetails.setEmail(orderDetailsDto.getEmail());
        orderDetails.setFirstname(orderDetailsDto.getFirstname());
        orderDetails.setLastname(orderDetailsDto.getLastname());
        order.setOrderDetails(orderDetails);

        doReturn(order).when(orderRepository).getById(UUID.fromString(orderDetailsDto.getOrderId()));
        doReturn(orderDetails).when(orderDetailsService).findById(orderDetails.getId());
        doReturn(orderDetails).when(orderDetailsService).save(orderDetails);

        orderService.setOrderDetails(orderDetailsDto);

        assertThat(orderDetails).isEqualTo(order.getOrderDetails());

        verify(orderRepository).getById(UUID.fromString(orderDetailsDto.getOrderId()));
        verify(orderDetailsService).findById(orderDetails.getId());
        verify(orderDetailsService).save(orderDetails);
    }

    @Test
    void setOrderDetailsIfOrderDetailsDtoIsNullTest() {
        assertThatNullPointerException().isThrownBy(() -> orderService.setOrderDetails(null));
    }

    @Test
    void setOrderDetailsIfOrderIsNullTest() {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

        assertThatNullPointerException().isThrownBy(() -> orderService.setOrderDetails(orderDetailsDto));
    }
}
