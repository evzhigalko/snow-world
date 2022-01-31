package by.zhigalko.snow.world.dal.entity.snowboard;

import by.zhigalko.snow.world.dal.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.dal.entity.enums.RidingLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = false)
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
