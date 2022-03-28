package by.zhigalko.snow.world.entity.clothes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Куртка")
@Getter
@Setter
public class Jacket extends ClothesWithMembrane {
}
