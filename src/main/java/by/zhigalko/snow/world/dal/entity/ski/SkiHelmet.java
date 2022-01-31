package by.zhigalko.snow.world.dal.entity.ski;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Шлем лыжный")
public class SkiHelmet extends SkiGroup {
}
