package by.zhigalko.snow.world.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailsDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String orderId;
}
