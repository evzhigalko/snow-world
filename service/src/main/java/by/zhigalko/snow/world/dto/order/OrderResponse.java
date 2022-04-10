package by.zhigalko.snow.world.dto.order;

import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.OrderDetails;
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
