package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleRepositoryTest {
    private Role role;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp(){
        role = new Role();
        role.setRoleName(RoleName.USER);
        role.setId(UUID.randomUUID());
    }

    @Test
    void findByRoleNameTest() {
        doReturn(role).when(roleRepository).findByRoleName(RoleName.USER);

        Role actualRole = roleRepository.findByRoleName(RoleName.USER);

        assertThat(actualRole).isEqualTo(role);
    }
}
