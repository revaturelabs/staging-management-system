package com.revature.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {;
    private Logger log = Logger.getRootLogger();

    @Before("execution(* com.revature.*.*(..))")
    public void logCall(JoinPoint joinPoint){
        log.trace("Call made to: " + joinPoint.getSignature().getName());
    }
}
