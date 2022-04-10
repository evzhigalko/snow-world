package by.zhigalko.snow.world.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "orders", callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Table(name = "\"order_details\"")
@AttributeOverride(name = "id", column = @Column(name = "order_details_id"))
public class OrderDetails extends BaseEntity {
    @Column(name = "last_name")
    private String lastname;

    @Column(name = "first_name")
    private String firstname;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "orderDetails")
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setOrderDetails(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }
}
