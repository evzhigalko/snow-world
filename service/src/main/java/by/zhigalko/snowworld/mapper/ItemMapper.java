package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.response.ItemResponse;
import by.zhigalko.snowworld.entity.Item;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Set<ItemResponse> itemSetToItemResponseSet(Set<Item> itemSet);
    Set<Item> itemResponseSetToItemSet(Set<ItemResponse> itemResponseSet);
}
