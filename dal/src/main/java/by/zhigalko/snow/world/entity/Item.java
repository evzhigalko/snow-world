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
@EqualsAndHashCode(exclude = {"image"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "item")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Item extends BaseEntity{
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "item_cart",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "cart_id")})
    private Set<Cart> carts = new HashSet<>();

    public void addItem(Cart cart) {
        this.carts.add(cart);
        cart.getItems().add(this);
    }

    public void removeItem(Cart cart) {
        this.carts.remove(cart);
        cart.getItems().add(this);
    }
}
