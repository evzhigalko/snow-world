package by.zhigalko.snowworld.dto;

import by.zhigalko.snowworld.entity.Item;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {
    private UUID id;
    private LocalDate startReservationDate;
    private int reservationDayNumber;
    private double totalSum;
    private Set<Item> items;
}
