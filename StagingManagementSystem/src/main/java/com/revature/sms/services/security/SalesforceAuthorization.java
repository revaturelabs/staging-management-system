package com.revature.sms.services.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.sms.security.models.SalesforceUser;

/**
 * Created by louislopez on 1/18/17.
 * Modified for SMS by Richard Orr on 8/10/2017
 */

@Controller
@Scope("prototype")
public class SalesforceAuthorization extends Helper implements Authorization {
	@Value("${sms.sf.login}")
	private String loginURL;
	@Value("services/oauth2/authorize")
	private String authURL;
	@Value("services/oauth2/token")
	private String accessTokenURL;
	@Value("${sms.sf.clientkey}")
	private String clientId;
	@Value("${sms.sf.clientsecret}")
	private String clientSecret;
	@Value("${sms.sfredirect}")
	private String redirectUri;
	@Value("${sms.sf.smsurl}")
	private String redirectUrl;
	@Value("services/oauth2/revoke")
	private String revokeUrl;
	
	private static final String REDIRECT = "redirect:";
	private static final String REVATURE = "http://www.revature.com/";

	public SalesforceAuthorization() {
		super();
	}

	/**
	 * Redirects the request to perform authentication.
	 * 
	 */
	@RequestMapping("/salesforce")
	public ModelAndView openAuthURI() {

		return new ModelAndView(REDIRECT + loginURL + authURL + "?response_type=code&client_id=" + clientId
				+ "&redirect_uri=" + redirectUri);
	}

	/**
	 * Retrieves Salesforce authentication token from Salesforce REST API
	 * 
	 * @param code
	 * @param servletResponse
	 */
	@RequestMapping("/authenticated")
	public ModelAndView generateSalesforceToken(@RequestParam(value = "code") String code,
			HttpServletResponse servletResponse) throws IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(loginURL + accessTokenURL);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
		parameters.add(new BasicNameValuePair("code", code));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = httpClient.execute(post);
		String token = URLEncoder.encode(toJsonString(response.getEntity().getContent()), "UTF-8");
		servletResponse.addCookie(new Cookie("token", token));
		return new ModelAndView(REDIRECT + redirectUrl);

	}

	/**
	 * Clears session information and logout the user.
	 * 
	 * Note: Still retrieving 302 on access-token and null refresh-token
	 * 
	 * @param auth
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/revoke", method = RequestMethod.GET)
	public ModelAndView revoke(Authentication auth, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws IOException, ServletException {
		if (auth == null)
			return new ModelAndView(REDIRECT + REVATURE);
		/*if (!debug) {
			// revoke all tokens from the Salesforce
			String accessToken = ((SalesforceUser) auth.getPrincipal()).getSalesforceToken().getAccessToken();
			revokeToken(accessToken);
		}*/

		// logout and clear Spring Security Context
		servletRequest.logout();
		SecurityContextHolder.clearContext();
		return new ModelAndView(REDIRECT + REVATURE);
	}

	/*private void revokeToken(String token) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(loginURL + revokeUrl);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("token", token));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = httpClient.execute(post);
	}*/

	public void setAuthURL(String authURL) {
		this.authURL = authURL;
	}

	public void setAccessTokenURL(String accessTokenURL) {
		this.accessTokenURL = accessTokenURL;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}
