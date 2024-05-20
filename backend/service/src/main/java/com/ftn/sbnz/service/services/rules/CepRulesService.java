package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.event.TransactionEvent;
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
        kSession.insert( new Client(1L, "PERA", "PERIC", "pera@gmail.com",  "123"));

        // client 1 transactions
        kSession.insert(new TransactionEvent(1L, 5000.0, Timestamp.valueOf("2024-05-11 10:00:00"), TransactionEvent.Type.INCOME, 1L));
        kSession.insert(new TransactionEvent(2L, 2000.0, Timestamp.valueOf("2024-05-13 15:00:00"), TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(3L, 1000.0, Timestamp.valueOf("2024-05-12 20:00:00"), TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(4L, 3000.0, Timestamp.valueOf("2024-05-13 09:00:00"), TransactionEvent.Type.INCOME, 1L));
        kSession.insert(new TransactionEvent(5L, 1500.0, Timestamp.valueOf("2024-05-14 14:00:00"), TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(6L, 4000.0, Timestamp.valueOf("2024-05-11 18:00:00"), TransactionEvent.Type.INCOME, 1L));
        kSession.insert(new TransactionEvent(7L, 2500.0, Timestamp.valueOf("2024-05-13 13:00:00"), TransactionEvent.Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(8L, 3500.0, Timestamp.valueOf("2024-05-15 11:00:00"), TransactionEvent.Type.INCOME, 1L));

        // client 2 transactions
        kSession.insert(new TransactionEvent(9L, 10000.0, Timestamp.valueOf("2024-05-13 10:00:00"), TransactionEvent.Type.INCOME, 2L));
        kSession.insert(new TransactionEvent(10L, 5000.0, Timestamp.valueOf("2024-05-13 15:00:00"), TransactionEvent.Type.OUTCOME, 2L));
        kSession.insert(new TransactionEvent(11L, 2000.0, Timestamp.valueOf("2024-05-13 20:00:00"), TransactionEvent.Type.OUTCOME, 2L));
        kSession.insert(new TransactionEvent(12L, 4000.0, Timestamp.valueOf("2024-05-14 09:00:00"), TransactionEvent.Type.INCOME, 2L));
        kSession.insert(new TransactionEvent(13L, 1000.0, Timestamp.valueOf("2024-05-13 14:00:00"), TransactionEvent.Type.OUTCOME, 2L));
        kSession.insert(new TransactionEvent(14L, 3000.0, Timestamp.valueOf("2024-05-12 18:00:00"), TransactionEvent.Type.INCOME, 2L));
        kSession.insert(new TransactionEvent(15L, 1500.0, Timestamp.valueOf("2024-05-13 13:00:00"), TransactionEvent.Type.OUTCOME, 2L));
        kSession.insert(new TransactionEvent(16L, 2000.0, Timestamp.valueOf("2024-05-15 11:00:00"), TransactionEvent.Type.INCOME, 2L));

        // financial goal
        kSession.insert(new FinancialGoal(1L, "Putovanje", "Štednja za letovanje", Timestamp.valueOf("2024-05-01 00:00:00"), 20000.0, Timestamp.valueOf("2024-09-01 00:00:00"), 5000.0, 50.0, 1L));
        kSession.insert(new FinancialGoal(2L, "Automobil", "Kupovina novog automobila", Timestamp.valueOf("2024-05-01 00:00:00"), 50000.0, Timestamp.valueOf("2025-05-01 00:00:00"), 10000.0, 0.0, 2L));

        kSession.fireAllRules();
    }
}
