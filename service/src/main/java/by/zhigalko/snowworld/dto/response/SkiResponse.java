package by.zhigalko.snowworld.dto.response;

import by.zhigalko.snowworld.model.RidingLevel;
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
