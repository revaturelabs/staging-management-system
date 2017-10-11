package com.revature.sms.services;

import java.io.IOException;

/**
 * Project: SMS (taken from caliber)
 *
 * @author d4k1d23
 * @date 1/25/17
 */
@FunctionalInterface
public interface SalesforceUserDetailsService {
    /**
     * Gets the Salesforce user
     * @param accessToken
     * @param endpoint the id field of the salesforce token
     * @return JSON representation of the salesforce user
     * @throws IOException
     */
    String getSalesforceUser(String accessToken, String endpoint) throws IOException;
}
