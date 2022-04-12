package by.zhigalko.snow.world.dto.response;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.model.Product;
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
    private Product productName;
    private Image image;
    private String gender;
    private String availableToRental;
    private String cost;
    private EquipmentSize equipmentSize;
}
