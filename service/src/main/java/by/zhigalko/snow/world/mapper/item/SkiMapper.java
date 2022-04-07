package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.SkiRequest;
import by.zhigalko.snow.world.dto.item.response.SkiResponse;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class SkiMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(skiRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Ski skiRequestToSki(SkiRequest skiRequest);

    public abstract SkiResponse skiToSkiResponse(Ski ski);

    public abstract List<SkiResponse> skiListToSkiResponseList(List<Ski> skiList);
}
