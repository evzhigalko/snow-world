package by.zhigalko.snow.world.entity.clothes;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ClothesWithMembrane extends ClothesGroup {
    @Column(name = "membrane")
    private int membrane;
}
