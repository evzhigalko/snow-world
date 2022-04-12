package by.zhigalko.snow.world.service;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.model.RoleName;

/**
 * Service for Role
 */
public interface RoleService {
    /**
     * Find role by
     * @param roleName {@link RoleName}
     * @return {@link Role}
     */
    Role findByRoleName(RoleName roleName);
}
