package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.entities.Marketer;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.MarketerRepo;

@Component
public class MarketerServiceImpl implements MarketerService {
    @Autowired
    private MarketerRepo marketerRepo;
    @Autowired
    private CredentialRepo credentialRepo;

    @Override
    public void addMarketer(Marketer marketer) {
        marketerRepo.saveAndFlush(marketer);
    }

    @Override
    public Set<Marketer> getAllMarketers() {
        return new HashSet<Marketer>(marketerRepo.findAll());
    }
    
    @Override
    public void addAllMarketers(Set<Marketer> makerters) {
      for(Marketer m : makerters){
        m.setCredential(credentialRepo.saveAndFlush(m.getCredential()));
        marketerRepo.saveAndFlush(m);
      }
    }
}
