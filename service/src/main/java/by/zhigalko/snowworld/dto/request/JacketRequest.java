package by.zhigalko.snowworld.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JacketRequest extends ItemRequest {
    private String membrane;
}
