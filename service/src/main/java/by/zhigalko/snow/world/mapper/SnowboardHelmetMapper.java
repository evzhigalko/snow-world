package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SnowboardHelmetRequest;
import by.zhigalko.snow.world.dto.item.response.SnowboardHelmetResponse;
import by.zhigalko.snow.world.dto.item.response.SnowboardResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.entity.snowboard.SnowboardHelmet;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
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
