package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.SnowboardBootRequest;
import by.zhigalko.snowworld.dto.response.SnowboardBootResponse;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.entity.SnowboardBoot;
import by.zhigalko.snowworld.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SnowboardBootMapper {

    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(snowboardBootRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract SnowboardBoot snowboardBootRequestToSnowboardBoot(SnowboardBootRequest snowboardBootRequest);

    public abstract SnowboardBootResponse snowboardToSnowboardBootResponse(SnowboardBoot snowboardBoot);

    public abstract List<SnowboardBootResponse> snowboardBootListToSnowboardBootResponseList(List<SnowboardBoot> snowboardBootList);
}
