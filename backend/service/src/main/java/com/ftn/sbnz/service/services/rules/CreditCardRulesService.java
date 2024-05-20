package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.model.Transaction.Type;

@Service
public class CreditCardRulesService {
    private final KieContainer kieContainer;

    @Autowired
    public CreditCardRulesService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules() {
        System.out.println("LAAAAAAAAAAAAAAAAAA");
        KieSession kSession = kieContainer.newKieSession("ksession-credit-card");
        kSession.insert( new Client(1L, "PERA", "PERIC", "pera@gmail.com",  "123"));
        kSession.insert( new CreditCard(1L, 2000.00, 1L));
        kSession.insert( new Transaction(1L, 200.00, Timestamp.valueOf(LocalDateTime.now()), Type.INCOME, 1L));
        kSession.insert( new Transaction(2L, 400.00, Timestamp.valueOf(LocalDateTime.now()), Type.OUTCOME, 1L));

        kSession.fireAllRules();
    }
}
