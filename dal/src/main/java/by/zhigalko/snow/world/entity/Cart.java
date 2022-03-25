package by.zhigalko.snow.world.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "cart")
@AttributeOverride(name = "id", column = @Column(name = "cart_id"))
public class Cart extends BaseEntity {
    @Column(name = "start_reservation_date")
    private LocalDateTime startReservationDate;

    @Column(name = "day_number")
    private int reservationDayNumber;

    @Column(name = "total_sum")
    private double totalSum;

    @OneToOne(mappedBy = "cart")
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "carts")
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        this.items.add(item);
        item.getCarts().add(this);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.getCarts().remove(this);
    }
}
