package by.zhigalko.snowworld.dto.response;

import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.entity.OrderDetails;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
    private UUID orderId;
    private LocalDate startReservationDate;
    private int reservationDayNumber;
    private double totalSum;
    private OrderDetails orderDetails;
    private Set<Item> items;
}
