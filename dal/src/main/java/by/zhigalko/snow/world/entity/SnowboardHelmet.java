package by.zhigalko.snow.world.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Шлем сноубордический")
@Getter
@Setter
public class SnowboardHelmet extends SnowboardGroup {
}
