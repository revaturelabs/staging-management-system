package com.revature.sms.services.security;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Martino on 1/25/2017.
 */
public interface Authorization {

    /**
     * Generates authURI with provided parameters
     * @return view of the generated URI
     */
    ModelAndView openAuthURI(); //TODO: Change javadoc to reflect changes
    /**
     * Creates salesforce token and saves it as a session cookie
     * @param code the string returned from the authURI required for getting token from salesforce
     * @param httpServletResponse
     * @return back to the application
     * @throws IOException
     * @throws URISyntaxException 
     */
    ModelAndView generateSalesforceToken(String code,  HttpSession session, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws IOException, URISyntaxException;
}