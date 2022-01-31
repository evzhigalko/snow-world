package by.zhigalko.snow.world.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "items")
@Table(name = "image")
@AttributeOverride(name = "id", column = @Column(name = "image_id"))
public class Image extends BaseEntity {
    @Column(name = "image_name")
    private String imageName;

    @OneToMany(mappedBy = "image")
    private Set<Item> items = new HashSet<>();

    public void addImage(Item item) {
        this.items.add(item);
        item.setImage(this);
    }
    public void removeImage(Item item) {
        this.items.remove(item);
    }
}
