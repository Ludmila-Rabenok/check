package ru.clevertec.check.aspectj;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* service.impl.*.*(..))")
    public void serviceMethods() {}
}
