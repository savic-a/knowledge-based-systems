package com.ftn.sbnz.service.services;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.service.repositories.FinancialGoalRepository;


@Service
public class FinancialGoalService {
    private final KieContainer kieContainer;

    @Autowired
    public FinancialGoalRepository repository;

    @Autowired
    public FinancialGoalService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<FinancialGoal> getAll() {
        return this.repository.findAll();
    }
}
