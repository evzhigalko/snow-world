package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import by.zhigalko.snowworld.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    private Role role;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(UUID.randomUUID());
        role.setRoleName(RoleName.USER);
    }

    @Test
    void findByRoleNameIfRoleNameIsNotNullTest() {
        doReturn(role).when(roleRepository).findByRoleName(RoleName.USER);

        Role actual = roleService.findByRoleName(RoleName.USER);

        assertThat(actual).isEqualTo(role);

        verify(roleRepository).findByRoleName(RoleName.USER);
    }

    @Test
    void findByRoleNameIfRoleNameIsNullTest() {
        doThrow(NullPointerException.class).when(roleRepository).findByRoleName(null);

        assertThatNullPointerException().isThrownBy(() -> roleService.findByRoleName(null));

        verify(roleRepository).findByRoleName(null);
    }

    @Test
    void findByRoleNameIfRoleNameIsNotExistTest() {
        doThrow(EntityNotFoundException.class).when(roleRepository).findByRoleName(RoleName.ADMIN);

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> roleService.findByRoleName(RoleName.ADMIN));

        verify(roleRepository).findByRoleName(RoleName.ADMIN);
    }
}
