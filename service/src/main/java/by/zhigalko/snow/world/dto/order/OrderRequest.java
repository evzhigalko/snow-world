package by.zhigalko.snow.world.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
    private String cartId;
    private String startReservationDate;
    private String reservationDayNumber;
    private String totalSum;
    private String items;
}
