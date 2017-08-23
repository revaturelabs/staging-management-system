package com.revature.sms.services.security;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Created by Martino on 1/25/2017.
 */
public interface Authorization {

    /**
     * Generates authURI with provided parameters
     * @return view of the generated URI
     */
    ModelAndView openAuthURI( HttpServletResponse servletResponse)  throws JsonProcessingException, UnsupportedEncodingException ;
    /**
     * Creates salesforce token, uses it to get a salesforce user, saves that as a cookie on the client.
     * @param code the string returned from the authURI required for getting token from salesforce
     * @param httpServletResponse
     * @return back to the application
     * @throws IOException
     * @throws URISyntaxException 
     */
    ModelAndView generateSalesforceToken(String code,  HttpSession session, HttpServletResponse httpServletResponse) throws IOException, URISyntaxException;
}