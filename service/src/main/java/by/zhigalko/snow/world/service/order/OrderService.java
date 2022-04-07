package by.zhigalko.snow.world.service.order;

import by.zhigalko.snow.world.dto.order.OrderRequest;
import by.zhigalko.snow.world.dto.order.OrderResponse;
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
     * @return {@link OrderResponse}
     */
    OrderResponse findById(UUID id);

    /**
     * Find all user orders by
     * @param userId {@link UUID} is received from {@link Cart} id
     * @return {@link List<Order>}
     */
    List<OrderResponse> findAllByUserId(UUID userId);

    /**
     * Save order in database
     * @param orderRequest {@link OrderRequest}
     * @return saved {@link OrderResponse}
     */
    OrderResponse save(OrderRequest orderRequest);

    /**
     * Delete order from database
     * @param orderRequest {@link OrderRequest}
     */
    void delete(OrderRequest orderRequest);
}
