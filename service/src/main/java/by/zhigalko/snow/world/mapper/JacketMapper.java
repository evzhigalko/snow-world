package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.JacketRequest;
import by.zhigalko.snow.world.dto.item.response.JacketResponse;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class JacketMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(Product.valueOf(jacketRequest.getProductName()))", target = "productName")
    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(jacketRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Jacket jacketRequestToJacket(JacketRequest jacketRequest);

    public abstract JacketResponse jacketToJacketResponse(Jacket jacket);
}
