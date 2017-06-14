package com.revature.services;

import com.revature.entities.Marketer;

import java.util.Set;

public interface MarketerService {
    public void addMarketer(Marketer marketer);
    public Set<Marketer> getAllMarketers();
    void addAllMarketers(Set<Marketer> makerter);
}
