package by.zhigalko.snow.world.dto.item;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String id;
    private String maker;
    private String productName;
    private Image image;
    private String gender;
    private String availableToRental;
    private String cost;
    private EquipmentSize equipmentSize;
}
