package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.MittenRequest;
import by.zhigalko.snow.world.dto.item.response.MittenResponse;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class MittenMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(mittenRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Mitten mittenRequestToMitten(MittenRequest mittenRequest);

    public abstract MittenResponse mittenToMittenResponse(Mitten mitten);

    public abstract List<MittenResponse> mittenListToMittenResponseList(List<Mitten> mittenList);
}
