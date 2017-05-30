package com.revature.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {

		/* Redirect on the successful authentication of the user */
		Collection<? extends GrantedAuthority> auths = authResult.getAuthorities();
		// loop through the authentications
		for (GrantedAuthority grantedAuthority : auths) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				response.sendRedirect("/StagingManagementSystem/superuserhome.jsp");
			}
			if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
				response.sendRedirect("/StagingManagementSystem/managerhome.jsp");
			}
		}

	}
}