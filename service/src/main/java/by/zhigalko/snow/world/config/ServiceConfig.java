package by.zhigalko.snow.world.config;

import by.zhigalko.snow.world.aspect.ServiceAspect;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Log4j2
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceConfig {
    @Bean
    public ServiceAspect serviceAspect() {
        log.debug("Service aspect initialized");
        return new ServiceAspect();
    }
}
