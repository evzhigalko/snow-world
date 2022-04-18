package by.zhigalko.snowworld.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PantsResponse  extends ItemResponse {
    private int membrane;
}
