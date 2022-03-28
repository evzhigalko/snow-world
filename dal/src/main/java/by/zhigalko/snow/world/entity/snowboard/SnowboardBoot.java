package by.zhigalko.snow.world.entity.snowboard;

import by.zhigalko.snow.world.entity.enums.LacingSystem;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Ботинки сноубордические")
public class SnowboardBoot extends SnowboardGroup {
    @Column(name = "lacing_system")
    @Enumerated(EnumType.STRING)
    private LacingSystem lacingSystem;
}
