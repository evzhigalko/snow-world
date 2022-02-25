package by.zhigalko.snow.world.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = "equipmentSizeId",callSuper = false)
public abstract class Equipment extends Item {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_size_id", nullable = false)
    private EquipmentSize equipmentSizeId;
}
