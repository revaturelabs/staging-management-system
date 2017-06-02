package com.revature.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Created by Mykola Nikitin on 6/2/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private RestSavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{//TODO un-break this
        httpSecurity
                .csrf().disable()
                .formLogin()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/","/static/**").permitAll()
                    .antMatchers("/profile","/checkin","/checkout").hasRole("ASSOCIATE")
                    .antMatchers("/checkin/approve","/**").hasAnyRole("MANAGER","ADMIN")
                    .anyRequest().authenticated();
    }
}
