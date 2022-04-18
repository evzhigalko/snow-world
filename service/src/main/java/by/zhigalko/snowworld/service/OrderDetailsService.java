package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.entity.OrderDetails;

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
}
