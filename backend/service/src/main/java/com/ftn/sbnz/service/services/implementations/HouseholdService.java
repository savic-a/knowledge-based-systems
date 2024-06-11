package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.service.repositories.HouseholdRepository;
import com.ftn.sbnz.service.services.interfaces.IService;

@Service
public class HouseholdService implements IService<Household>{

    private final KieContainer kieContainer;

    @Autowired
    public HouseholdRepository repository;

    @Autowired
    public HouseholdService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Household> getAll() {
        return this.repository.findAll();
    }
    
}
