package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.model.ProductGroup;
import by.zhigalko.snowworld.repository.EquipmentSizeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipmentSizeServiceImplTest {
    @InjectMocks
    private EquipmentSizeServiceImpl equipmentSizeService;

    @Mock
    private EquipmentSizeRepository equipmentSizeRepository;

    private EquipmentSize equipmentSize;

    @BeforeEach
    void setUp() {
        equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("ID");
        equipmentSize.setProductGroup(ProductGroup.SNOWBOARD);
        equipmentSize.setUserMaxHeight(180);
        equipmentSize.setUserMinHeight(165);
    }

    @Test
    void findEquipmentSizeByIdIfEquipmentSizeIsExistTest() {
        doReturn(equipmentSize).when(equipmentSizeRepository).getById(equipmentSize.getEquipmentSizeId());

        EquipmentSize equipmentSize = equipmentSizeService.findEquipmentSizeById(this.equipmentSize.getEquipmentSizeId());

        assertThat(this.equipmentSize).isEqualTo(equipmentSize);
    }

    @Test
    void findEquipmentSizeByIdIfEquipmentSizeIsNotExistTest() {
        doThrow(EntityNotFoundException.class).when(equipmentSizeRepository).getById(anyString());

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> equipmentSizeService.findEquipmentSizeById(anyString()));
    }

    @Test
    void findEquipmentSizeByIdIfEquipmentSizeIsNullTest() {
        doThrow(IllegalArgumentException.class).when(equipmentSizeRepository).getById(null);

        assertThatIllegalArgumentException().isThrownBy(() -> equipmentSizeService.findEquipmentSizeById(null));
    }
}
