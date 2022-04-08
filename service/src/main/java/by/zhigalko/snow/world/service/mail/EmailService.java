package by.zhigalko.snow.world.service.mail;

import by.zhigalko.snow.world.dto.OrderDetailsDto;

/**
 * Service for sending order by email
 */
public interface EmailService {
    /**
     * Send message with order details to admin email
//     * @param from {@link String} the sender of the message. The value is received from order value
     */
    void sendMessage(OrderDetailsDto orderDetailsDto);
}
