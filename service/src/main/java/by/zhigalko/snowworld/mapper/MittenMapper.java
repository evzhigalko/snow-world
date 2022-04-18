package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.MittenRequest;
import by.zhigalko.snowworld.dto.response.MittenResponse;
import by.zhigalko.snowworld.entity.Mitten;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
