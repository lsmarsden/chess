package org.example.allure;

import io.qameta.allure.Allure;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

//TODO Figure out how to configure this
@Aspect
public class AllureSuiteAspect {
    @Around("@annotation(org.junit.jupiter.api.Test) && execution(* *(..))")
    public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AllureSuite suite = signature.getMethod().getAnnotation(AllureSuite.class);
        if (suite != null) {
            System.out.println("Applying suite " + suite.value());
            Allure.suite(suite.value());
        }
        return joinPoint.proceed();
    }
}
