package by.zhigalko.snow.world.dal.entity.snowboard;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Шлем сноубордический")
@Getter
@Setter
public class SnowboardHelmet extends SnowboardGroup {
}
