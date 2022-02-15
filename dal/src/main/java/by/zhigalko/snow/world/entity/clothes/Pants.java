package by.zhigalko.snow.world.entity.clothes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
