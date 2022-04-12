package by.zhigalko.snow.world.service.impl;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.model.RoleName;
import by.zhigalko.snow.world.repository.RoleRepository;
import by.zhigalko.snow.world.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
