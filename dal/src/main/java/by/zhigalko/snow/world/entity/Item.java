package by.zhigalko.snow.world.entity;

import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import javax.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "carts", callSuper = true)
@EqualsAndHashCode(exclude = {"image", "equipmentSize"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "item")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Item extends BaseEntity {
    @Column(name = "product_name")
    @Enumerated(EnumType.STRING)
    private Product productName;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Column(name = "maker")
    private String maker;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "available_to_rental")
    private boolean availableToRental;

    @Column(name = "cost")
    private double cost;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "equipment_size_id", nullable = false)
    private EquipmentSize equipmentSize;

    @ManyToMany(mappedBy = "items")
    private Set<Cart> carts = new HashSet<>();

    @ManyToMany(mappedBy = "orderItems")
    private Set<Order> orders = new HashSet<>();
}
