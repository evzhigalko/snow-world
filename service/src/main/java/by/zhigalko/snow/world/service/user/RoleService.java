package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.stereotype.Service;

/**
 * Service for Role
 */
@Service
public interface RoleService {
    /**
     * Find role by
     * @param roleName {@link RoleName}
     * @return {@link Role}
     */
    Role findByRoleName(RoleName roleName);
}
