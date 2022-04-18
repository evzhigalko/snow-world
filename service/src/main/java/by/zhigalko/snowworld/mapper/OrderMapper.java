package by.zhigalko.snowworld.mapper;

import by.zhigalko.snowworld.dto.request.OrderRequest;
import by.zhigalko.snowworld.dto.response.OrderResponse;
import by.zhigalko.snowworld.entity.Order;
import by.zhigalko.snowworld.entity.OrderDetails;
import by.zhigalko.snowworld.service.CartService;
import by.zhigalko.snowworld.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, OrderDetails.class})
public abstract class OrderMapper {
    @Autowired
    protected CartService cartService;

    @Autowired
    protected UserService userService;

    @Mapping(expression = "java(cartService.findCartById(UUID.fromString(orderRequest.getCartId())).getItems() )", target = "items")
    @Mapping(expression = "java(userService.findById(UUID.fromString(orderRequest.getCartId())))", target = "user")
    public abstract Order orderRequestToOrder(OrderRequest orderRequest);

    @Mapping(source = "order.id", target = "orderId")
    public abstract OrderResponse orderToOrderResponse(Order order);

    public abstract List<OrderResponse> orderListToOrderResponseList(List<Order> orderList);
}
