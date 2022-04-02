package by.zhigalko.snow.world.dto.item.response;

import by.zhigalko.snow.world.entity.enums.LacingSystem;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SnowboardBootResponse extends ItemResponse {
    private LacingSystem lacingSystem;
}
