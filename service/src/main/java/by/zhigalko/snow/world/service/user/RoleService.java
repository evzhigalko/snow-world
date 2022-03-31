package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.stereotype.Service;

/**
 * Service for Role
 */
@Service
public interface RoleService {
    Role findByRoleName(RoleName roleName);
}
