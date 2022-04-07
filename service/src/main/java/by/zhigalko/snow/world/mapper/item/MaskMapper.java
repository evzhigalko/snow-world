package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.MaskRequest;
import by.zhigalko.snow.world.dto.item.response.MaskResponse;
import by.zhigalko.snow.world.entity.clothes.Mask;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class MaskMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(maskRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Mask maskRequestToMask(MaskRequest maskRequest);

    public abstract MaskResponse maskToMaskResponse(Mask mask);

    public abstract List<MaskResponse> maskListToMaskResponseList(List<Mask> maskList);
}
