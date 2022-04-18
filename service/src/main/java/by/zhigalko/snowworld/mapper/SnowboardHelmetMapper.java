package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SnowboardHelmetRequest;
import by.zhigalko.snowworld.dto.response.SnowboardHelmetResponse;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SnowboardHelmet;
import by.zhigalko.snowworld.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SnowboardHelmetMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(snowboardHelmetRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract SnowboardHelmet snowboardHelmetRequestToSnowboardHelmet(SnowboardHelmetRequest snowboardHelmetRequest);

    public abstract SnowboardHelmetResponse snowboardToSnowboardHelmetResponse(SnowboardHelmet snowboardHelmet);

    public abstract List<SnowboardHelmetResponse> snowboardHelmetListToSnowboardHelmetResponseList(List<SnowboardHelmet> snowboardHelmetList);
}
