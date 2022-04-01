package by.zhigalko.snow.world.dto.item;

import by.zhigalko.snow.world.entity.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class ItemDto {
    private String id;
    private String maker;
    private String product;
    private Image image;
    private String gender;
    private String availableToRental;
    private String cost;
}
