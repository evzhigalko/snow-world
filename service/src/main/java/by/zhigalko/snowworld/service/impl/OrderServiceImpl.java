package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.dto.OrderDetailsDto;
import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Order;
import by.zhigalko.snowworld.entity.OrderDetails;
import by.zhigalko.snowworld.mapper.OrderMapper;
import by.zhigalko.snowworld.repository.OrderRepository;
import by.zhigalko.snowworld.service.OrderDetailsService;
import by.zhigalko.snowworld.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailsService orderDetailsService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailsService = orderDetailsService;
    }

    @Override
    public OrderResponse findById(UUID id) {
        Order order = orderRepository.getById(id);
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
        Order order = orderRepository.getById(UUID.fromString(orderDetailsDto.getOrderId()));
        OrderDetails details = orderDetailsService.findById(order.getOrderDetails().getId());
        details.setPhoneNumber(orderDetailsDto.getPhoneNumber());
        details.setFirstname(orderDetailsDto.getFirstname());
        details.setLastname(orderDetailsDto.getLastname());
        details.setEmail(orderDetailsDto.getEmail());
        details.addOrder(order);
        OrderDetails savedOrderDetails = orderDetailsService.save(details);
        order.setOrderDetails(savedOrderDetails);
    }
}
