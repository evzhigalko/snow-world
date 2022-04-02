package by.zhigalko.snow.world.dto.item.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MittenResponse extends ItemResponse {
    private int membrane;
}
