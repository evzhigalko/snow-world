package by.zhigalko.snow.world.dto.item.response;

import by.zhigalko.snow.world.entity.enums.RidingLevel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SkiResponse extends ItemResponse {
    private RidingLevel ridingLevel;
}
