package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
	AuthSuccessHandler auth = (AuthSuccessHandler) ctx.getBean("authSuccessHandler");


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("manager").password("password").roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().
		antMatchers("/superuserhome**").
		access("hasRole('ROLE_ADMIN')")
		.antMatchers("/managerhome**").
		access("hasRole('ROLE_MANAGER')")
		.and().formLogin()
		.loginPage("/login")
		.successHandler(auth);

	}

}
