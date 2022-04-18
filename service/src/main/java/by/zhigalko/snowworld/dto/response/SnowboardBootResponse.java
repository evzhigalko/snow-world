package by.zhigalko.snowworld.dto.response;

import by.zhigalko.snowworld.model.LacingSystem;
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
