package by.zhigalko.snow.world.entity.clothes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Штаны")
@Getter
@Setter
public class Pants extends ClothesWithMembrane {
}
