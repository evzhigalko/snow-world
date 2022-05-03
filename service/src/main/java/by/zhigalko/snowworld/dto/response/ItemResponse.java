package by.zhigalko.snowworld.dto.response;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.model.Product;
import lombok.*;
import java.util.Map;
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
    private Map<String, Object> itemInformation;
}
