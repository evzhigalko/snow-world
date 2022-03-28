package by.zhigalko.snow.world.entity.ski;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Шлем лыжный")
public class SkiHelmet extends SkiGroup {
}
