package by.zhigalko.snow.world.dal.entity.clothes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Варежки")
@Getter
@Setter
public class Mitten extends ClothesWithMembrane {

}
