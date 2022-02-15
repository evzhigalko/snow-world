package by.zhigalko.snow.world.entity;

import by.zhigalko.snow.world.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "users",callSuper = true)
@Table(name = "role")
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity {
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.setRole(this);
    }
}
