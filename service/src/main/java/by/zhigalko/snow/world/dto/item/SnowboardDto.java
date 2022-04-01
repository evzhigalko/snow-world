package by.zhigalko.snow.world.dto.item;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SnowboardDto extends ItemDto {
    private String ridingLevel;
    private String hardnessLevel;
}
