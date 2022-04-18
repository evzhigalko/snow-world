package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findByRoleName(RoleName roleName);
}
