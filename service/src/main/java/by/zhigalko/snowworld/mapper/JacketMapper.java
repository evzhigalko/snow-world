package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.JacketRequest;
import by.zhigalko.snowworld.dto.response.JacketResponse;
import by.zhigalko.snowworld.entity.Jacket;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.service.EquipmentSizeService;
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
