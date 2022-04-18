package by.zhigalko.snowworld.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SnowboardRequest extends ItemRequest {
    private String ridingLevel;
    private String hardnessLevel;
}
