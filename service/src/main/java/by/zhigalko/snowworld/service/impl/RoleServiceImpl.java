package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import by.zhigalko.snowworld.repository.RoleRepository;
import by.zhigalko.snowworld.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
