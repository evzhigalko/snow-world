package by.zhigalko.snow.world.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = "equipmentSizeId", callSuper = true)
public abstract class Equipment extends Item {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "equipment_size_id", nullable = false)
    private EquipmentSize equipmentSizeId;
}
