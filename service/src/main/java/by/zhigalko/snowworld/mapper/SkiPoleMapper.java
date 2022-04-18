package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiPoleRequest;
import by.zhigalko.snowworld.dto.response.SkiPoleResponse;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiPole;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
