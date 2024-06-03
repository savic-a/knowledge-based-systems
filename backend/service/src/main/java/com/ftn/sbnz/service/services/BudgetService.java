package com.ftn.sbnz.service.services;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.service.repositories.BudgetRepository;

@Service
public class BudgetService {
    private final KieContainer kieContainer;

    @Autowired
    public BudgetRepository repository;

    @Autowired
    public BudgetService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Budget> getAll() {
        return this.repository.findAll();
    }
}
