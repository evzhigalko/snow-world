package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.CapRequest;
import by.zhigalko.snow.world.dto.item.response.CapResponse;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class CapMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(capRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Cap capRequestToCap(CapRequest capRequest);

    public abstract CapResponse capToCapResponse(Cap cap);

    public abstract List<CapResponse> capListToCapResponseList(List<Cap> capList);
}
