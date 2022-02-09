package by.zhigalko.snow.world.entity.clothes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Куртка")
@Getter
@Setter
public class Jacket extends ClothesWithMembrane {

}
