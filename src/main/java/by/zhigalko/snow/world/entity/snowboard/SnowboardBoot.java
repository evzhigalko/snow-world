package by.zhigalko.snow.world.entity.snowboard;

import by.zhigalko.snow.world.entity.enums.LacingSystem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Ботинки сноубордические")
public class SnowboardBoot extends SnowboardGroup {
    @Column(name = "lacing_system")
    @Enumerated(EnumType.STRING)
    private LacingSystem lacingSystem;
}
