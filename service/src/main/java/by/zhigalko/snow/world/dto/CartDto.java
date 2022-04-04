package by.zhigalko.snow.world.dto;

import by.zhigalko.snow.world.entity.Item;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "items")
public class CartDto {
    private UUID id;
    private LocalDate startReservationDate;
    private int reservationDayNumber;
    private double totalSum;
    private Set<Item> items;
}
