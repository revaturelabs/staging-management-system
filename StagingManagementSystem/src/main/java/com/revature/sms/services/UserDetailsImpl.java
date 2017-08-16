package com.revature.sms.services;

import com.revature.sms.services.UserDetails;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Project: SMS
 *
 * @author d4k1d23
 * @date 1/25/17
 * 
 * Copied from caliber to be used with SMS by Stephan Kritikos
 */
@RestController
@Scope("session")
public class UserDetailsImpl extends Helper implements UserDetails {
    private HttpClient httpClient;
    private HttpResponse response;

    public UserDetailsImpl() {
        httpClient = HttpClientBuilder.create().build();
    }

    @RequestMapping(value = "/getSalesforceUser/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSalesforceUser(@RequestParam String accessToken,
                                    @RequestParam String endpoint) throws IOException {
        HttpGet get = new HttpGet(endpoint + "?access_token=" + accessToken);
        response = httpClient.execute(get);
        return toJsonString(response.getEntity().getContent());
    }
}
