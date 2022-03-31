package by.zhigalko.snow.world.dto.user;

import by.zhigalko.snow.world.entity.BaseEntity;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.Role;
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
    private Cart cart;
    private Role role;
}
