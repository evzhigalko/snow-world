package by.zhigalko.snow.world.dal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "equipmentSet")
@Table(name = "equipment_size")
public class EquipmentSize {
    @Id
    @Column(name = "equipment_size_id")
    private String equipmentSizeId;

    @OneToMany(mappedBy = "equipmentSizeId")
    private Set<Equipment> equipmentSet = new HashSet<>();

    @Column(name = "user_min_height")
    private Integer userMinHeight;

    @Column(name = "user_max_height")
    private Integer userMaxHeight;

    @Column(name = "user_min_weight")
    private Integer userMinWeight;

    @Column(name = "user_max_weight")
    private Integer userMaxWeight;

    public void addEquipment(Equipment equipment) {
        this.equipmentSet.add(equipment);
        equipment.setEquipmentSizeId(this);
    }
}
