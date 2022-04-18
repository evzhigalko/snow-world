package by.zhigalko.snowworld.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PantsRequest extends ItemRequest {
    private String membrane;
}
