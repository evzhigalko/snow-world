package by.zhigalko.snow.world.dto.user;

import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Cart cart;
}
