package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.response.ItemResponse;
import by.zhigalko.snow.world.entity.Item;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Set<ItemResponse> itemSetToItemResponseSet(Set<Item> itemSet);
    Set<Item> itemResponseSetToItemSet(Set<ItemResponse> itemResponseSet);
}
