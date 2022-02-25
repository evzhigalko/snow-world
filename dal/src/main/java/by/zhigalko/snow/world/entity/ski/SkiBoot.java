package by.zhigalko.snow.world.entity.ski;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Ботинки лыжные")
public class SkiBoot extends SkiGroup {
}
