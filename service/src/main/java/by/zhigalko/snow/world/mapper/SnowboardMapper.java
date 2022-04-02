package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.item.response.SnowboardResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SnowboardMapper extends ItemMapper {

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(snowboardRequest.getEquipmentSize()))",
            target = "equipmentSize")
    @Mapping(expression = "java(UUID.randomUUID())", target = "image.id")
    @Mapping(source = "imageName", target = "image.imageName")
    public abstract Snowboard snowboardRequestToSnowboard(SnowboardRequest snowboardRequest);

    public abstract SnowboardResponse snowboardToSnowboardResponse(Snowboard snowboard);
}
