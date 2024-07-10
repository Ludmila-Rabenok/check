package ru.clevertec.check.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
@Slf4j
public class MyAspect {
    @Before("Pointcuts.serviceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Entering method: " + methodName + " with arguments: " + args);
    }

    @AfterReturning(pointcut = "Pointcuts.serviceMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Exiting method: " + methodName + " with result: " + result);
    }
}
