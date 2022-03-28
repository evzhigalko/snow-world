package by.zhigalko.snow.world.entity.clothes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Шапка")
public class Cap extends ClothesGroup {
}
