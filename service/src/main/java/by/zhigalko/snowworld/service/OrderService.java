package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.dto.OrderDetailsDto;
import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Cart;
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
     * @return {@link List<OrderResponse>}
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

    /**
     * Set orderDetails to order
     * @param orderDetailsDto {@link OrderDetailsDto} is received from presentation layer,
     */
    void setOrderDetails(OrderDetailsDto orderDetailsDto);
}
