package by.zhigalko.snowworld.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Варежки")
@Getter
@Setter
public class Mitten extends ClothesWithMembrane {
}
