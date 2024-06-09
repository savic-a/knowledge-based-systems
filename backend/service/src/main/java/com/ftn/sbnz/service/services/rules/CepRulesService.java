package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.event.TransactionEvent;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.FinancialGoal;

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

        kSession.insert(new TransactionEvent(1L, 25000, TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(2L, 5000, TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(3L, 60000, TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(4L, 50000, TransactionEvent.Type.OUTCOME, 1L));

        kSession.fireAllRules();
    }
}
