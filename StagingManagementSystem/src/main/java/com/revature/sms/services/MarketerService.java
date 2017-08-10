package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Marketer;

public interface MarketerService {

    public void addMarketer(Marketer marketer);
    public Set<Marketer> getAllMarketers();
    void addAllMarketers(Set<Marketer> makerter);
}
