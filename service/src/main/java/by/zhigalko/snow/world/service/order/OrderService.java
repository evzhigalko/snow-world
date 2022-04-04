package by.zhigalko.snow.world.service.order;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Order;
import java.util.List;
import java.util.UUID;

/**
 * Service for order
 */
public interface OrderService {
    /**
     * Find order by
     * @param id {@link UUID}
     * @return {@link Order}
     */
    Order findById(UUID id);

    /**
     * Find all user orders by
     * @param userId {@link UUID} is received from {@link Cart} id
     * @return {@link List<Order>}
     */
    List<Order> findAllByUserId(UUID userId);

    /**
     * Save order in database
     * @param order {@link Order}
     * @return saved {@link Order}
     */
    Order save(Order order);

    /**
     * Delete order from database
     * @param order {@link Order}
     */
    void delete(Order order);
}
