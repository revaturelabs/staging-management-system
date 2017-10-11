package com.revature.sms.services.security;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Credential;
import com.revature.sms.entities.Manager;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.CredentialRepo;
import com.revature.sms.repositories.ManagerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykola Nikitin on 6/2/17.
 * Loads a user from the database, and gives them their role in Spring Security.
 */
@Service("userDetailsService")
public class CredentialDetailsService implements UserDetailsService {

    @Autowired
    private AssociateRepo associateRepo;

    @Autowired
    private CredentialRepo credentialRepo;

    @Autowired
    private ManagerRepo managerRepo;

    @Override //TODO:Tied into authentication, needs to be replaced
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Associate associate = associateRepo.getByCredential_Username(s);
        Manager manager = managerRepo.getByCredential_Username(s);
        Credential credential = null;
        List<String> auths = new ArrayList<>();
        if (associate != null) {
            auths.add("ASSOCIATE");
            credential = associate.getCredential();
        }
        if (manager != null) {
            auths.add(manager.getPermission().getLevel());
            credential = manager.getCredential();
        }
        if (credential == null) throw new UsernameNotFoundException("Unable to find user " + s);
        return new User(credential.getUsername(), credential.getPassword(), true, true, true, true, buildAuthority(auths));
    }

    private List<GrantedAuthority> buildAuthority(List<String> auths) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : auths) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
