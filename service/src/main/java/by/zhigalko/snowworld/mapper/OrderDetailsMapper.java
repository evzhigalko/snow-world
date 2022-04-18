package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.OrderDetailsDto;
import by.zhigalko.snowworld.entity.OrderDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDetails orderDetailsDtoToOrderDetails(OrderDetailsDto orderDetailsDto);
}
