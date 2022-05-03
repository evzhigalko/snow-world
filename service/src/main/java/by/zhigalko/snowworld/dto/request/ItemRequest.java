package by.zhigalko.snowworld.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private String maker;
    private String productName;
    private String gender;
    private String availableToRental;
    private String cost;
    private String equipmentSize;
    private String ridingLevel;
    private String lacingSystem;
    private String hardnessLevel;
    private String membrane;
}
