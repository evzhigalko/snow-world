package by.zhigalko.snow.world.dto.item.request;

import by.zhigalko.snow.world.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest extends BaseEntity {
    private String maker;
    private String productName;
    private String imageName;
    private String gender;
    private String availableToRental;
    private String cost;
    private String equipmentSize;
}
