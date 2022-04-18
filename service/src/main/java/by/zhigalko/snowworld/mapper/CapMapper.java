package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.CapRequest;
import by.zhigalko.snowworld.dto.response.CapResponse;
import by.zhigalko.snowworld.entity.Cap;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class CapMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(capRequest.getEquipmentSize()))",
            target = "equipmentSize")
    public abstract Cap capRequestToCap(CapRequest capRequest);

    public abstract CapResponse capToCapResponse(Cap cap);

    public abstract List<CapResponse> capListToCapResponseList(List<Cap> capList);
}
