package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.model.RoleName;

/**
 * Data Access Object for Role.
 */
public interface RoleDao extends BaseDaoSaveEntity<Role> {
    /**
     * Find role by roleName.
     * @param roleName username
     * @return role {@link Role}
     */
    Role find(RoleName roleName);
}
