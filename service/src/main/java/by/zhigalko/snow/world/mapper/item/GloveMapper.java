package by.zhigalko.snow.world.mapper.item;

import by.zhigalko.snow.world.dto.item.request.GloveRequest;
import by.zhigalko.snow.world.dto.item.response.GloveResponse;
import by.zhigalko.snow.world.entity.clothes.Glove;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class GloveMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(gloveRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Glove gloveRequestToGlove(GloveRequest gloveRequest);

    public abstract GloveResponse gloveToGloveResponse(Glove glove);

    public abstract List<GloveResponse> gloveListToGloveResponseList(List<Glove> gloveList);
}
