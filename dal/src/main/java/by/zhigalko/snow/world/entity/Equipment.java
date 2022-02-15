package by.zhigalko.snow.world.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(exclude = "equipmentSizeId",callSuper = false)
public abstract class Equipment extends Item {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_size_id", nullable = false)
    private EquipmentSize equipmentSizeId;
}
