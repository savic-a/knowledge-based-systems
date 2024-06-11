package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.model.Transaction;

@Service
public class CepRulesService {
    private final KieContainer kieContainer;

    @Autowired
    public CepRulesService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules() {
        KieSession kSession = kieContainer.newKieSession("ksession-cep");

        kSession.insert(new Client(1L, "PERA", "PERIC", "pera@gmail.com", "123", new ArrayList<>(), new ArrayList<>(), false, false));
    
        kSession.insert(new Budget(1L, 100000, 1L));

        kSession.insert(new FinancialGoal(1L, "Savings Goal", "Save money for a vacation", Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 120000, 50000, 1L));

        kSession.insert(new Transaction(1L, 25000, Timestamp.valueOf(LocalDateTime.now()), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(2L, 5000, Timestamp.valueOf(LocalDateTime.now()), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(3L, 60000, Timestamp.valueOf(LocalDateTime.now()), Transaction.Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert(new Transaction(4L, 50000, Timestamp.valueOf(LocalDateTime.now()), Transaction.Type.OUTCOME, Category.FOOD, 1L));

        kSession.fireAllRules();
    }
}
