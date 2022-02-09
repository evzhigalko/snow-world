package by.zhigalko.snow.world.dao.user;

import by.zhigalko.snow.world.dao.BaseDaoSaveEntity;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;

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
