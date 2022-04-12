package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.PantsRequest;
import by.zhigalko.snow.world.dto.response.PantsResponse;
import by.zhigalko.snow.world.entity.Pants;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class PantsMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(pantsRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Pants pantsRequestToPants(PantsRequest pantsRequest);

    public abstract PantsResponse pantsToPantsResponse(Pants pants);

    public abstract List<PantsResponse> pantsListToPantsResponseList(List<Pants> pantsList);
}
