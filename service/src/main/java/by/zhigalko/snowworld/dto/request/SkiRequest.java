package by.zhigalko.snowworld.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SkiRequest extends ItemRequest {
    private String ridingLevel;
}
