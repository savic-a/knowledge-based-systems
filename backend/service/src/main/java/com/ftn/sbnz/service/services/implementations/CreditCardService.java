package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.service.repositories.CreditCardRepository;
import com.ftn.sbnz.service.services.interfaces.IService;

@Service
public class CreditCardService implements IService<CreditCard>{
    private final KieContainer kieContainer;

    @Autowired
    public CreditCardRepository repository;

    @Autowired
    public CreditCardService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<CreditCard> getAll() {
        return repository.findAll();
    }
    
}
