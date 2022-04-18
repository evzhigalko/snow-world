package by.zhigalko.snowworld.entity;

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
@ToString(exclude = {"items", "user"}, callSuper = true)
@Table(name = "\"order\"")
@AttributeOverride(name = "id", column = @Column(name = "order_id", updatable = false, insertable = false))
public class Order extends BaseEntity {
    @Column(name = "start_reservation_date")
    private LocalDate startReservationDate;

    @Column(name = "day_number")
    private int reservationDayNumber;

    @Column(name = "total_sum")
    private double totalSum;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> items = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_details_id", nullable = false)
    private OrderDetails orderDetails;
}
