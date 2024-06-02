package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.event.TransactionEvent;
import com.ftn.sbnz.model.Budget;
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

        // // first rule
        // kSession.insert(new Budget(1L, 5000.0, 1L));
        // kSession.insert(new TransactionEvent(1L, 600.0, TransactionEvent.Type.OUTCOME, 1L));
        // kSession.insert(new TransactionEvent(2L, 700.0, TransactionEvent.Type.OUTCOME, 1L));
        // kSession.insert(new TransactionEvent(3L, 500.0, TransactionEvent.Type.OUTCOME, 1L));
        
        // // second rule
        // kSession.insert(new Budget(2L, 3000.0, 2L));
        // kSession.insert(new TransactionEvent(4L, 1000.0, TransactionEvent.Type.OUTCOME, 2L));
        // kSession.insert(new TransactionEvent(5L, 1500.0, TransactionEvent.Type.OUTCOME, 2L));
        // kSession.insert(new TransactionEvent(6L, 700.0, TransactionEvent.Type.OUTCOME, 2L));

        // // third rule
        // kSession.insert(new FinancialGoal(3L, "Putovanje", "Štednja za letovanje", Timestamp.valueOf("2024-01-01 00:00:00"), 10000.0, Timestamp.valueOf("2024-12-31 00:00:00"), 12000.0, 0.0, 3L));

        // CEP 2
        kSession.insert(new FinancialGoal(1L, "Putovanje", "Štednja za letovanje", Timestamp.valueOf("2024-05-01 00:00:00"), 20000.0, Timestamp.valueOf("2024-09-01 00:00:00"), 5000.0, 50.0, 4L));


        kSession.fireAllRules();
    }
}
