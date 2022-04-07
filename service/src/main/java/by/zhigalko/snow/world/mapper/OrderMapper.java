package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.order.OrderRequest;
import by.zhigalko.snow.world.dto.order.OrderResponse;
import by.zhigalko.snow.world.entity.Order;
import by.zhigalko.snow.world.service.cart.CartService;
import by.zhigalko.snow.world.service.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public abstract class OrderMapper {
    @Autowired
    protected CartService cartService;

    @Autowired
    protected UserService userService;

    @Mapping(expression = "java(cartService.findCartById(UUID.fromString(orderRequest.getCartId())).getItems() )", target = "items")
    @Mapping(expression = "java(userService.findById(UUID.fromString(orderRequest.getCartId())))", target = "user")
    public abstract Order orderRequestToOrder(OrderRequest orderRequest);
    public abstract OrderResponse orderToOrderResponse(Order order);
}
