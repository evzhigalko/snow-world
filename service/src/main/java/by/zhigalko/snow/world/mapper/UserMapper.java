package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.service.user.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", imports = {RoleService.class, Cart.class})
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;

    @Mapping(source = "role", target = "role.roleName")
    @Mapping(expression = "java(roleService.findByRoleName(RoleName.USER).getId())", target = "role.id")
    @Mapping(expression = "java(new Cart())", target = "newCart")
    public abstract User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "role.roleName", target = "role")
    public abstract UserResponse userToUserResponse(User user);
}
