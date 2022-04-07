package by.zhigalko.snow.world.service.order;

import by.zhigalko.snow.world.dto.order.OrderRequest;
import by.zhigalko.snow.world.dto.order.OrderResponse;
import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.mapper.OrderMapper;
import by.zhigalko.snow.world.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
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
}
