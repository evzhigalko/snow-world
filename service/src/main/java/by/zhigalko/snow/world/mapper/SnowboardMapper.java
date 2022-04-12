package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.SnowboardRequest;
import by.zhigalko.snow.world.dto.response.SnowboardResponse;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.entity.Snowboard;
import by.zhigalko.snow.world.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SnowboardMapper  {

    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(snowboardRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Snowboard snowboardRequestToSnowboard(SnowboardRequest snowboardRequest);

    public abstract SnowboardResponse snowboardToSnowboardResponse(Snowboard snowboard);

    public abstract List<SnowboardResponse> snowboardListToSnowboardResponseList(List<Snowboard> snowboardList);
}
