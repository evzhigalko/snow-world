package by.zhigalko.snowworld.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Перчатки")
@Getter
@Setter
public class Glove extends ClothesWithMembrane {
}
