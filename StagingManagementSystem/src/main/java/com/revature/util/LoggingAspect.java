package com.revature.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {;
    private Logger log = Logger.getRootLogger();

    @NoLogging
    @Pointcut("execution(* com.revature.controllers..*(..))" +
            "&& !execution(* com.revature.util.*.*(..))")
    public void mostEverything(){/*Nope!~*/}

    @NoLogging
    @Before("mostEverything()")
    public void logCall(JoinPoint joinPoint){
        log.trace("Call made to: " + joinPoint.getSignature().getName());
    }

    @NoLogging
    @AfterThrowing("mostEverything()")
    public void thrown(JoinPoint joinPoint){
        log.error("Exception thrown: " + joinPoint.getSignature().getName());
    }
}
