package by.zhigalko.snow.world.dal.entity.ski;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
