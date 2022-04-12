package by.zhigalko.snow.world.repository;

import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findByRoleName(RoleName roleName);
}
