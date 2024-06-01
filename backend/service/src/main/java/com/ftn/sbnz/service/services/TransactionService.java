package com.ftn.sbnz.service.services;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.service.repositories.TransactionRepository;

@Service
public class TransactionService {
    private final KieContainer kieContainer;

    @Autowired
    public TransactionRepository repository;

    @Autowired
    public TransactionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Transaction> getAll() {
        return this.repository.findAll();
    }
    
}
