package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiHelmetRequest;
import by.zhigalko.snowworld.dto.response.SkiHelmetResponse;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiHelmet;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
