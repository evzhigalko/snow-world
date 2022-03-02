package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.dao.user.RoleDaoImpl;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleService(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    public Role findByRoleName(RoleName roleName) {
        return roleDao.find(roleName);
    }
}
