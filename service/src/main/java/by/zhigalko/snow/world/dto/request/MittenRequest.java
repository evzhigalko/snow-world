package by.zhigalko.snow.world.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MittenRequest extends ItemRequest {
    private String membrane;
}
