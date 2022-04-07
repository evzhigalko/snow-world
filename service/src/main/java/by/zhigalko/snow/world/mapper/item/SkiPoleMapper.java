package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.SkiPoleRequest;
import by.zhigalko.snow.world.dto.item.response.SkiPoleResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiPole;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SkiPoleMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(skiPoleRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract SkiPole skiPoleRequestToSkiPole(SkiPoleRequest skiPoleRequest);

    public abstract SkiPoleResponse skiPoleToSKiPoleResponse(SkiPole skiPole);

    public abstract List<SkiPoleResponse> skiPoleListToSkiPoleResponseList(List<SkiPole> skiPoleList);
}
