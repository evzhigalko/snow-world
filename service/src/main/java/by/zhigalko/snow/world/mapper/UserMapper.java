package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.service.user.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", imports = {Cart.class})
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(source = "role", target = "role.roleName")
    @Mapping(expression = "java(roleService.findByRoleName(RoleName.USER).getId())", target = "role.id")
    @Mapping(expression = "java(new Cart())", target = "cart")
    @Mapping(target = "newCart", ignore = true)
    @Mapping(expression = "java(passwordEncoder.encode(\"password\"))", target = "password")
    public abstract User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "role.roleName", target = "role")
    public abstract UserResponse userToUserResponse(User user);
}
