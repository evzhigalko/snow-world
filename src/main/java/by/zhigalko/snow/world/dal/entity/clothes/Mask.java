package by.zhigalko.snow.world.dal.entity.clothes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Маска")
public class Mask extends ClothesGroup {

}
