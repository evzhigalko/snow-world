package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.SkiBootRequest;
import by.zhigalko.snow.world.dto.item.response.SkiBootResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SkiBootMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(skiBootRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract SkiBoot skiBootRequestToSkiBoot(SkiBootRequest skiBootRequest);

    public abstract SkiBootResponse skiBootToSkiBootResponse(SkiBoot skiBoot);

    public abstract List<SkiBootResponse> skiBootListToSkiBootResponseList(List<SkiBoot> skiBootList);
}
