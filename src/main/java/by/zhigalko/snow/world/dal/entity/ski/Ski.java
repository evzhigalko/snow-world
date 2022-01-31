package by.zhigalko.snow.world.dal.entity.ski;

import by.zhigalko.snow.world.dal.entity.enums.RidingLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Лыжи")
public class Ski extends SkiGroup {
    @Column(name = "riding_level")
    @Enumerated(EnumType.STRING)
    private RidingLevel ridingLevel;
}
