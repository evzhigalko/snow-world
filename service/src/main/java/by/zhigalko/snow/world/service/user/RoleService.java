package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
