package by.zhigalko.snow.world.service.user;

import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username);
        log.fatal("Found user by username: "  + foundUser);
        if (foundUser == null) {
            throw new UsernameNotFoundException("Пользователь не существует!");
        }
        return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(), getUserAuthorities(foundUser));
    }

    private Set<GrantedAuthority> getUserAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));
        return grantedAuthorities;
    }
}
