package by.zhigalko.snow.world.mapper;

import by.zhigalko.snow.world.dto.user.UserRequest;
import by.zhigalko.snow.world.dto.user.UserResponse;
import by.zhigalko.snow.world.entity.Cart;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.service.user.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Mapper(componentModel = "spring", imports = {Cart.class, RoleName.class})
public abstract class UserMapper {
    @Autowired
    protected RoleService roleService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(expression = "java(new Cart())", target = "newCart")
    @Mapping(expression = "java(passwordEncoder.encode(userRequest.getPassword()))", target = "password")
    @Mapping(expression = "java(roleService.findByRoleName(RoleName.USER).getId())", target = "role.id")
    @Mapping(expression = "java(roleService.findByRoleName(RoleName.USER).getRoleName())", target = "role.roleName")
    public abstract User userRequestToUser(UserRequest userRequest);

    public abstract UserResponse userToUserResponse(User user);
    
    public abstract List<UserResponse> userListToUserResponseList(List<User> userList);
}
