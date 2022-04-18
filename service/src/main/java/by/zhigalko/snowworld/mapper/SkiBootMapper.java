package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SkiBootRequest;
import by.zhigalko.snowworld.dto.response.SkiBootResponse;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SkiBoot;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
