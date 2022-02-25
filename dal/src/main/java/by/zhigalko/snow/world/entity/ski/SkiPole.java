package by.zhigalko.snow.world.entity.ski;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Палки лыжные")
public class SkiPole extends SkiGroup {
}
