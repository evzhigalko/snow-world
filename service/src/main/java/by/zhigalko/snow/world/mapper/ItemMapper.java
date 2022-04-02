package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.request.ItemRequest;
import by.zhigalko.snow.world.dto.item.response.ItemResponse;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.Item;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.service.common.equipment_size.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class, Image.class})
public abstract class ItemMapper {

    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    @Mapping(expression = "java(itemRequest.getId())", target = "item.id")
    @Mapping(expression = "java(Product.valueOf(itemRequest.getProductName()))", target = "productName")
    @Mapping(expression = "java(new Image())", target = "image")
    @Mapping(expression = "java(UUID.randomUUID())", target = "image.id")
    @Mapping(source = "imageName", target = "image.imageName")
    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(itemRequest.getEquipmentSize()))",
            target = "equipmentSize")
    @Mapping(target = "carts", ignore = true)
    public abstract Item itemDtoToItem(ItemRequest itemRequest);

    public abstract ItemResponse itemToItemDto(Item item);
}
