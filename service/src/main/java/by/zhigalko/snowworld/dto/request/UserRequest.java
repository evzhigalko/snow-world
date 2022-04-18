package by.zhigalko.snowworld.dto.request;

import by.zhigalko.snowworld.entity.BaseEntity;
import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserRequest extends BaseEntity {
    @NotEmpty(message = "Введите имя пользователя")
    @Size(min = 5, max = 30, message = "Имя пользователя должны быть не менее 5 символов и не более 30")
    private String username;

    @NotEmpty(message = "Введите пароль")
    @Size(min = 5, message = "Пароль должен быть не менее 5 символов")
    private String password;

    @NotEmpty(message = "Введите электронный адрес")
    @Email(message = "Введен не корректный электронный адрес")
    private String email;

    @NotEmpty(message = "Введите телефонный номер")
    @Pattern(regexp = "^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$",
             message = "Введен не корректный телефонный номер")
    private String phoneNumber;

    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов")
    private String firstName;

    @NotEmpty(message = "Введите фамилию")
    @Size(min = 2, max = 32, message = "Фамилия должна быть не менее 2 символов")
    private String lastName;

    private String gender;
}
