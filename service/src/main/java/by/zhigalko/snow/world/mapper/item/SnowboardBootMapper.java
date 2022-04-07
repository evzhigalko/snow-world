package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.SnowboardBootRequest;
import by.zhigalko.snow.world.dto.item.response.SnowboardBootResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.snowboard.SnowboardBoot;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
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
