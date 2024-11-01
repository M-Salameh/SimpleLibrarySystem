package com.maids.LibrarySystem.AOPLogging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

    private static final Logger logger = LoggerFactory.getLogger(MyLogger.class);
    @Before("execution(* com.maids.LibrarySystem.Controllers..*(..))")
    public void logBefore(JoinPoint joinPoint)
    {
        logger.info("Entering method: " + joinPoint.getSignature());
    }

    @After("execution(* com.maids.LibrarySystem.Controllers..*(..))")
    public void logAfter(JoinPoint joinPoint)
    {
        logger.info("Exiting method: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.maids.LibrarySystem.Controllers..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result)
    {
        logger.debug("Method returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.maids.LibrarySystem.Controllers..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error)
    {
        logger.error("Exception in method: " + joinPoint.getSignature() + " with cause: " + error.getMessage());
    }
}