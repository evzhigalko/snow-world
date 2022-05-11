package by.zhigalko.snowworld.entity;

import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
import javax.persistence.*;
import by.zhigalko.snowworld.util.HashMapConverter;
import lombok.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude ={"carts", "orders"}, callSuper = true)
@EqualsAndHashCode(exclude = {"image", "equipmentSize"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item extends BaseEntity {
    @Column(name = "product_name")
    @Enumerated(EnumType.STRING)
    private Product productName;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
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

    @ManyToMany(mappedBy = "items")
    private Set<Order> orders = new HashSet<>();

    @Convert(converter = HashMapConverter.class)
    @Column(name = "item_information")
    private Map<String, Object> itemInformation = new HashMap<>();

    public void addProperty(String key, String value) {
        itemInformation.put(key, value);
    }
}
