package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.item.ItemDto;
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

    @Mapping(expression = "java(UUID.fromString(itemDto.getId()))", target = "id")
    @Mapping(expression = "java(Product.valueOf(itemDto.getProductName()))", target = "productName")
    @Mapping(expression = "java(new Image())", target = "image")
    @Mapping(expression = "java(UUID.randomUUID())", target = "image.id")
    @Mapping(target = "carts", ignore = true)
    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(itemDto.getEquipmentSize().getEquipmentSizeId()))", target = "equipmentSize")
    public abstract Item itemDtoToItem(ItemDto itemDto);

    @Mapping(expression = "java(String.valueOf(item.getId()))", target = "id")
    public abstract ItemDto itemToItemDto(Item item);
}
