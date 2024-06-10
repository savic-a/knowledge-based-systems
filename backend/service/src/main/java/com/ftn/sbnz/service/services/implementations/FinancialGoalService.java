package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.service.repositories.FinancialGoalRepository;
import com.ftn.sbnz.service.services.interfaces.IService;


@Service
public class FinancialGoalService implements IService<FinancialGoal> {
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

    public FinancialGoal getFinancialGoalByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }
}
