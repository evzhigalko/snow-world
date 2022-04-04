package by.zhigalko.snow.world.service.order;

import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(NoResultException::new);
    }

    @Override
    public List<Order> findAllByUserId(UUID userId) {
        return orderRepository.findByUserIdOrderByStartReservationDate(userId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
