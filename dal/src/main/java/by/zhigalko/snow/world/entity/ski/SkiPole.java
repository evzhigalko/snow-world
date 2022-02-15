package by.zhigalko.snow.world.entity.ski;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
