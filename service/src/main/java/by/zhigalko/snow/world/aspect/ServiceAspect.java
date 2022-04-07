package by.zhigalko.snow.world.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Log4j2
@Aspect
public class ServiceAspect {
    @Pointcut("within(by.zhigalko.snow.world.service..*)")
    public void allMethodsPointcut() {}

    @Before("allMethodsPointcut()")
    public void getAllAdvice(JoinPoint joinPoint) {
        log.debug("Advice: {}, Pointcut: {}", "`@Before`", "all method `*` by.zhigalko.snow.world.service");
        log.debug("JoinPoint: {}, Args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }
}
