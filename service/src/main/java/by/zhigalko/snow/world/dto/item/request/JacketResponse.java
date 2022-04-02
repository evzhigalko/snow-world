package by.zhigalko.snow.world.dto.item.request;

import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JacketResponse extends ItemResponse {
    private int membrane;
}
