package by.zhigalko.snowworld.service.impl;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.entity.User;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.RoleName;
import by.zhigalko.snowworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    private User user;
    private SimpleGrantedAuthority grantedAuthority;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("alex-s");
        user.setPassword("qwerty");
        user.setEmail("test@test.com");
        user.setPhoneNumber("+321456789");
        user.setFirstName("Alex");
        user.setLastName("Smith");
        user.setGender(Gender.MALE);
        Role role = new Role();
        role.setRoleName(RoleName.USER);
        user.setRole(role);
        grantedAuthority = new SimpleGrantedAuthority(user.getRole().getRoleName().name());
    }

    @Test
    void loadUserByUsernameIfUserIsNotNullAndUserIsExistTest() {
        doReturn(user).when(userRepository).findByUsername(user.getUsername());

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().contains(grantedAuthority.getAuthority()))).isTrue();

        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    void loadUserByUsernameIfUsernameIsNullTest() {
        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> userDetailsService.loadUserByUsername(null));
    }

    @Test
    void loadUserByUsernameIfUserNotFoundTest() {
        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> userDetailsService.loadUserByUsername("dummy"));
    }
}
