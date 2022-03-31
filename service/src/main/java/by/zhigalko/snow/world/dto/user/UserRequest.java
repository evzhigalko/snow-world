package by.zhigalko.snow.world.dto.user;

import by.zhigalko.snow.world.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserRequest extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
}
