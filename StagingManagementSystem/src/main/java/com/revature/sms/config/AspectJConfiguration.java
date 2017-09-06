package com.revature.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.revature.sms.util.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectJConfiguration {

    @Bean
    public LoggingAspect getLoggingAspect() {

        return new LoggingAspect();
    }
}
