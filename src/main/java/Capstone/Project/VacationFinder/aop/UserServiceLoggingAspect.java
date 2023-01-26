package Capstone.Project.VacationFinder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceLoggingAspect.class);

    /**
     * Sets pointcut to be any user service method used.
     */
    @Pointcut(value = "execution(* Capstone.Project.VacationFinder.services.UserService.*(..))")
    private void pointCutAllUserServiceMethods() {
    }

    /**
     * Runs advice before a user service method is executed.
     *
     * @param joinPoint is a user service method that is being executed.
     */
    @Before("pointCutAllUserServiceMethods()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("Before advice: " + joinPoint.getSignature().getName());
    }

    /**
     * Runs advice after a user service method is executed.
     *
     * @param joinPoint is a user service method that is being executed.
     */
    @After("pointCutAllUserServiceMethods()")
    public void logAfterAdvice(JoinPoint joinPoint) {
        LOGGER.info("After advice: " + joinPoint.getSignature().getName());
    }

    /**
     * Runs advice after a user service method is executed and returns a user.
     *
     * @param joinPoint is a user service method that is being executed.
     * @param users     a user returned from the database.
     */
    @AfterReturning(value = "pointCutAllUserServiceMethods()", returning = "users")
    public void afterReturningAdvice(JoinPoint joinPoint, Object users) {
        LOGGER.info("After Returning Advice: Method Name: " + joinPoint.getSignature().getName());
        LOGGER.info("Result: " + users);
    }

}
