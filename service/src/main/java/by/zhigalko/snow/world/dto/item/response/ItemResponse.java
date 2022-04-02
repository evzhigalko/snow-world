package by.zhigalko.snow.world.dto.item.response;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private UUID id;
    private String maker;
    private String productName;
    private Image image;
    private String gender;
    private String availableToRental;
    private String cost;
    private EquipmentSize equipmentSize;
}
