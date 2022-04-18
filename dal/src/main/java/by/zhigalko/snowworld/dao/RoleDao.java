package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;

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
