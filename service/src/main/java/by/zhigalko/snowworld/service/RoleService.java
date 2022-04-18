package by.zhigalko.snowworld.service;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;

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
