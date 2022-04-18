package by.zhigalko.snowworld.dto.response;

import by.zhigalko.snowworld.dto.CartDto;
import by.zhigalko.snowworld.entity.BaseEntity;
import by.zhigalko.snowworld.entity.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserResponse extends BaseEntity {
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private CartDto cartDto;
    private Role role;
}
