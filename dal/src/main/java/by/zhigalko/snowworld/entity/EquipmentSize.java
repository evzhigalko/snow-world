package by.zhigalko.snowworld.entity;

import javax.persistence.*;
import by.zhigalko.snowworld.model.ProductGroup;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"itemSet"})
@Table(name = "equipment_size")
public class EquipmentSize {
    @Id
    @Column(name = "equipment_size_id")
    private String equipmentSizeId;

    @OneToMany(mappedBy = "equipmentSize")
    private Set<Item> itemSet = new HashSet<>();

    @Column(name = "user_min_height")
    private Integer userMinHeight;

    @Column(name = "user_max_height")
    private Integer userMaxHeight;

    @Column(name = "user_min_weight")
    private Integer userMinWeight;

    @Column(name = "user_max_weight")
    private Integer userMaxWeight;

    @Column(name = "product_group")
    @Enumerated(EnumType.STRING)
    private ProductGroup productGroup;

    public void addItem(Item item) {
        this.itemSet.add(item);
        item.setEquipmentSize(this);
    }
}
