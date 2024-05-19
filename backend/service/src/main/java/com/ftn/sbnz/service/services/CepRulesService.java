package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.event.TransactionEvent;
import com.ftn.sbnz.event.TransactionEvent.Type;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.Client;

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
        
        Budget budget = new Budget(1L, 1000.0, 1L);
        kSession.insert(budget);

        kSession.insert(new TransactionEvent(1L, 300.00, Type.OUTCOME, 1L));
        kSession.insert(new TransactionEvent(1L, 250.0, Type.OUTCOME, 1L)); 
        kSession.insert(new TransactionEvent(1L, 50.0, Type.OUTCOME, 1L)); 
        kSession.insert(new TransactionEvent(1L, 500.0, Type.OUTCOME, 1L)); 

        kSession.fireAllRules();
    }
}
