package by.zhigalko.snowworld.entity;

import by.zhigalko.snowworld.model.HardnessLevel;
import by.zhigalko.snowworld.model.RidingLevel;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Сноуборд")
public class Snowboard extends SnowboardGroup{
    @Column(name = "riding_level")
    @Enumerated(EnumType.STRING)
    private RidingLevel ridingLevel;

    @Column(name = "hardness_level")
    @Enumerated(EnumType.STRING)
    private HardnessLevel hardnessLevel;
}
