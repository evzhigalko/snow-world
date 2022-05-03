package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.ItemRequest;
import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Item;
import by.zhigalko.snowworld.model.Product;
import by.zhigalko.snowworld.service.EquipmentSizeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Product.class})
public abstract class ItemMapper {
    @Autowired
    protected EquipmentSizeService equipmentSizeService;

    public abstract Set<ItemResponse> itemSetToItemResponseSet(Set<Item> itemSet);

    public abstract Set<Item> itemResponseSetToItemSet(Set<ItemResponse> itemResponseSet);

    @Mapping(expression = "java(equipmentSizeService.findEquipmentSizeById(itemRequest.getEquipmentSize()))",
            target = "equipmentSize")
    @Mapping(expression = "java(new HashMap<String, Object>())", target = "itemInformation")
    public abstract Item itemRequestToItem(ItemRequest itemRequest);

    public abstract ItemResponse itemToItemResponse(Item item);
}