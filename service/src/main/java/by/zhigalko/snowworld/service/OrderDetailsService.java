package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.entity.OrderDetails;
import java.util.UUID;

/**
 * Service for OrderDetails
 */
public interface OrderDetailsService {
    /**
     * Save order details
     * @param orderDetails {@link OrderDetails} is received from presentation layer
     * @return {@link OrderDetails}
     */
    OrderDetails save(OrderDetails orderDetails);

    /**
     * Find order details by id
     * @param orderDetailsId {@link UUID}
     * @return entity {@link OrderDetails}
     */
    OrderDetails findById(UUID orderDetailsId);
}
