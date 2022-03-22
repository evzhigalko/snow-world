package by.zhigalko.snow.world.config;

import by.zhigalko.snow.world.entity.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin").hasAuthority(RoleName.ADMIN.name())
//                .antMatchers("/user").hasAuthority(RoleName.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/welcome")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
        http.userDetailsService(userDetailsService);
    }
}
