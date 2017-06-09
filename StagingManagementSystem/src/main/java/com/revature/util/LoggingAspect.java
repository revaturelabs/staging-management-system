package com.revature.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
//@Component
public class LoggingAspect {;
    private Logger log = Logger.getRootLogger();

    @Pointcut("execution(* com.revature..*(..)) && !execution(* com.revature.config.AspectJConfiguration.*(..))")
    public void mostEverything(){/*Nope!~*/}

    @Before("mostEverything()")
    public void logCall(JoinPoint joinPoint){
        System.out.println("AAA");
        log.trace("Call made to: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing("mostEverything()")
    public void thrown(JoinPoint joinPoint){
        log.error("Exception thrown: " + joinPoint.getSignature().getName());
    }
}
