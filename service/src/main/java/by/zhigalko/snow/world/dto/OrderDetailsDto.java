package by.zhigalko.snow.world.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailsDto {
    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов")
    private String firstname;

    @NotEmpty(message = "Введите фамилию")
    @Size(min = 2, max = 32, message = "Фамилия должна быть не менее 2 символов")
    private String lastname;

    @NotEmpty(message = "Введите электронный адрес")
    @Email(message = "Введен не корректный электронный адрес")
    private String email;

    @NotEmpty(message = "Введите телефонный номер")
    @Pattern(regexp = "^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$",
            message = "Введен не корректный телефонный номер")
    private String phoneNumber;

    private String orderId;
}
