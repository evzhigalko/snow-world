package by.zhigalko.snow.world.service.order;

import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(NoResultException::new);
        log.info("Found order: " + order);
        return order;
    }

    @Override
    public List<Order> findAllByUserId(UUID userId) {
        List<Order> orderList = orderRepository.findByUserIdOrderByStartReservationDate(userId);
        log.info("Found order list ");
        return orderList;
    }

    @Override
    public Order save(Order order) {
        Order savedOrder = orderRepository.save(order);
        log.info("Saved order: " + savedOrder);
        return savedOrder;
    }

    @Override
    public void delete(Order order) {
        log.info("Deleted order: " + order);
        orderRepository.delete(order);
    }
}
