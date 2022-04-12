package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.request.JacketRequest;
import by.zhigalko.snow.world.dto.response.JacketResponse;
import by.zhigalko.snow.world.entity.Jacket;
import by.zhigalko.snow.world.model.Product;
import by.zhigalko.snow.world.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class JacketMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(jacketRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Jacket jacketRequestToJacket(JacketRequest jacketRequest);

    public abstract JacketResponse jacketToJacketResponse(Jacket jacket);

    public abstract List<JacketResponse> jacketListToJacketResponseList(List<Jacket> jacketList);
}
