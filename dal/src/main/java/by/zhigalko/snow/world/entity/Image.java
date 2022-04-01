package by.zhigalko.snow.world.entity;

import javax.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "items",callSuper = true)
@Table(name = "image")
@AttributeOverride(name = "id", column = @Column(name = "image_id"))
public class Image extends BaseEntity {
    @Column(name = "image_name")
    private String imageName;

    @OneToMany(mappedBy = "image")
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        this.items.add(item);
        item.setImage(this);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
}
