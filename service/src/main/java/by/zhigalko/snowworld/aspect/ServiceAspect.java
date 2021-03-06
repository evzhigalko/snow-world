package by.zhigalko.snowworld.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class ServiceAspect {
    @Pointcut("within(by.zhigalko.snowworld.service..*)")
    public void allMethodsPointcut() {}

    @Before("allMethodsPointcut()")
    public void getAllAdvice(JoinPoint joinPoint) {
        log.info("Advice: {}, Pointcut: {}", "`@Before`", "all method `*` by.zhigalko.snowworld.service");
        log.info("JoinPoint: {}, Args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }
}
