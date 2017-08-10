package com.revature.sms.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    private Logger log = Logger.getRootLogger();

    @Pointcut("execution(* com.revature..*(..)) && !execution(* com.revature.sms.config.AspectJConfiguration.*(..))")
    public void mostEverything() {
        /* Nope!~ */
    }

    @Before("mostEverything()")
    public void logCall(JoinPoint joinPoint) {

        log.trace("Call made to: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing("mostEverything()")
    public void thrown(JoinPoint joinPoint) {

        log.error("Exception thrown: " + joinPoint.getSignature().getName());
    }
}
