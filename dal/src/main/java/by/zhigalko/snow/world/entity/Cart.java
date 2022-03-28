package by.zhigalko.snow.world.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "items")
@Table(name = "cart")
@AttributeOverride(name = "id", column = @Column(name = "cart_id"))
public class Cart extends BaseEntity {
    @Column(name = "start_reservation_date")
    private LocalDate startReservationDate;

    @Column(name = "day_number")
    private int reservationDayNumber;

    @Column(name = "total_sum")
    private double totalSum;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "cart_item",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> items = new HashSet<>();
}
