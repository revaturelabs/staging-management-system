package com.revature.services.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mykola Nikitin on 6/2/17.
 */
@Component
public class RestSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //super.onAuthenticationSuccess(request, response, authentication);
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(null == savedRequest){
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if(isAlwaysUseDefaultTargetUrl() || (null != targetUrlParameter && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request,response);
            clearAuthenticationAttributes(request);
            return;
        }
        clearAuthenticationAttributes(request);

    }

    public void setRequestCache(RequestCache requestCache){
        this.requestCache = requestCache;
    }
}
