package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.dto.request.OrderRequest;
import by.zhigalko.snow.world.dto.response.OrderResponse;
import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.entity.OrderDetails;
import by.zhigalko.snow.world.mapper.OrderDetailsMapper;
import by.zhigalko.snow.world.mapper.OrderMapper;
import by.zhigalko.snow.world.repository.OrderRepository;
import by.zhigalko.snow.world.service.OrderDetailsService;
import by.zhigalko.snow.world.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailsMapper orderDetailsMapper, OrderDetailsService orderDetailsService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailsMapper = orderDetailsMapper;
        this.orderDetailsService = orderDetailsService;
    }

    @Override
    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(NoResultException::new);
        log.info("Found order: " + order);
        return orderMapper.orderToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> findAllByUserId(UUID userId) {
        List<Order> orderList = orderRepository.findByUserIdOrderByStartReservationDate(userId);
        log.info("Found order list ");
        return orderMapper.orderListToOrderResponseList(orderList);
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = orderMapper.orderRequestToOrder(orderRequest);
        OrderDetails savedOrderDetails = orderDetailsService.save(new OrderDetails());
        order.setOrderDetails(savedOrderDetails);
        Order savedOrder = orderRepository.save(order);
        log.info("Saved order: " + savedOrder);
        return orderMapper.orderToOrderResponse(savedOrder);
    }

    @Override
    public void delete(OrderRequest orderRequest) {
        Order order = orderMapper.orderRequestToOrder(orderRequest);
        log.info("Deleted order: " + order);
        orderRepository.delete(order);
    }

    @Override
    public void setOrderDetails(OrderDetailsDto orderDetailsDto) {
        Order order = orderRepository.findById(UUID.fromString(orderDetailsDto.getOrderId())).orElseThrow(NoResultException::new);
        OrderDetails orderDetails = orderDetailsMapper.orderDetailsDtoToOrderDetails(orderDetailsDto);
        orderDetails.addOrder(order);
        OrderDetails savedOrderDetails = orderDetailsService.save(orderDetails);
        order.setOrderDetails(savedOrderDetails);
    }
}
