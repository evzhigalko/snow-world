package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.CartDto;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.service.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CartMapper {
    @Autowired
    protected UserService userService;

    @Mapping(expression = "java(userService.findById(cartDto.getId()))", target = "user")
    public abstract Cart cartDtoToCart(CartDto cartDto);

    public abstract CartDto cartToCartDto(Cart cart);
}
