package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.dto.OrderDetailsDto;

/**
 * Service for sending order by email
 */
public interface EmailService {
    /**
     * Send message with order details to admin email
     * @param orderDetailsDto {@link OrderDetailsDto}. The value is received from order and user input details
     */
    void sendMessage(OrderDetailsDto orderDetailsDto);
}
