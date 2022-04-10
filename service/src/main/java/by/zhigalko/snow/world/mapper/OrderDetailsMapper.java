package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.OrderDetailsDto;
import by.zhigalko.snow.world.entity.OrderDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDetails orderDetailsDtoToOrderDetails(OrderDetailsDto orderDetailsDto);
}
