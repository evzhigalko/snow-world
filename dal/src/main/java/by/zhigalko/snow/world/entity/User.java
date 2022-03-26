package by.zhigalko.snow.world.entity;

import by.zhigalko.snow.world.entity.enums.Gender;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@ToString(exclude = "cart", callSuper = true)
@EqualsAndHashCode(exclude = "role", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity{
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Cart cart;

    public void setNewCart(Cart cart) {
        if (cart == null) {
            if (this.cart != null) {
                this.cart.setUser(null);
            }
        }
        else {
            cart.setUser(this);
        }
        this.cart = cart;
    }
}
