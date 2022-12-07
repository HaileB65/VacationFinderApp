package Capstone.Project.VacationFinder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut(value = "execution(* Capstone.Project.VacationFinder.services.UserService.*(..))")
    private void pointCutAllUserServiceMethods(){
    }

    @Before("pointCutAllUserServiceMethods()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("Before advice: " + joinPoint.getSignature().getName());
    }

    @After("pointCutAllUserServiceMethods()")
    public void logAfterAdvice(JoinPoint joinPoint){
        LOGGER.info("After advice: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value="pointCutAllUserServiceMethods()", returning = "users")
    public void afterReturningAdvice(JoinPoint joinpoint, Object users){
        LOGGER.info("After Returning Advice: Method Name: " + joinpoint.getSignature().getName());
        LOGGER.info("Result: " + users);
    }

}
