package com.revature.sms.services.security;

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
 * Configuration class to get Spring Security to function.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private RestSavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // We need a password encoder, for which we use BCrypt.
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable() // Disable cross site request forgery
                .formLogin() // We want to use form-based authentication.
                .loginPage("/").successHandler(authenticationSuccessHandler) // Use the monstrosity of a class we have to handle successes.
                .failureHandler(new SimpleUrlAuthenticationFailureHandler()) // Handle failures with a 401.
                .and().logout() // Let anyone log out.
                .permitAll().and().authorizeRequests().antMatchers("/", "/home", "/static/**", "/**").permitAll() // Let anyone access static content, and root.
                .antMatchers("/profile", "/checkin", "/checkout").hasRole("ASSOCIATE") // Let associates look at their profile, and check in/out.
                .antMatchers("/checkin/approve").hasAnyRole("MANAGER", "ADMIN") // Let managers and admins approve checkins, and do anything.
                .anyRequest().authenticated();
    }
}
