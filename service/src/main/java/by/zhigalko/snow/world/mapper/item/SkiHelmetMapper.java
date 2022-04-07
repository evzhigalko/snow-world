package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.SkiHelmetRequest;
import by.zhigalko.snow.world.dto.item.response.SkiHelmetResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SkiHelmetMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(skiHelmetRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract SkiHelmet skiHelmetRequestToSkiHelmet(SkiHelmetRequest skiHelmetRequest);

    public abstract SkiHelmetResponse skiHelmetToSkiHelmetResponse(SkiHelmet skiHelmet);

    public abstract List<SkiHelmetResponse> skiHelmetListToSkiHelmetResponseList(List<SkiHelmet> skiHelmetList);
}
