package by.zhigalko.snow.world.repository;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    User findByUsernameAndEmail(String username, String email);
    List<User> findByRoleRoleName(RoleName roleName);
}
