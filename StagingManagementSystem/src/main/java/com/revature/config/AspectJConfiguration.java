package com.revature.config;

import com.revature.util.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJConfiguration {

    @Bean
    public LoggingAspect getLoggingAspect() {

        return new LoggingAspect();
    }
}
