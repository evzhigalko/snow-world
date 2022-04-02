package by.zhigalko.snow.world.dto.item.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SnowboardBootRequest extends ItemRequest {
    private String lacingSystem;
}
