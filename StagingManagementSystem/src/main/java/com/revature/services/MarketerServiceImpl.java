package com.revature.services;

import com.revature.entities.Marketer;
import com.revature.repositories.MarketerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MarketerServiceImpl implements MarketerService {
    @Autowired
    private MarketerRepo marketerRepo;

    @Override
    public void addMarketer(Marketer marketer) {
        marketerRepo.saveAndFlush(marketer);
    }

    @Override
    public Set<Marketer> getAllMarketers() {
        return marketerRepo.getAll();
    }
}
