package com.ftn.sbnz.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.time.SessionPseudoClock;

import com.ftn.sbnz.event.TransactionEvent;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.FinancialGoal;

public class CepRulesTest {
    
    @Test
    public void testCEPRules() {
    	KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        config.setOption(ClockTypeOption.get("pseudo"));
        KieSession ksession = kc.newKieSession("ksession-cep", config);

        SessionPseudoClock clock = ksession.getSessionClock();

        // Insert client and budget
        Long clientId = 1L;
        ksession.insert(new Client(clientId, "PERA", "PERIC", "pera@gmail.com", "123", new ArrayList<>(), new ArrayList<>(), false, false));
        ksession.insert(new Budget(clientId, 100000, clientId));

        // Insert financial goal
        ksession.insert(new FinancialGoal(clientId, "Savings Goal", "Save money for a vacation", 
                Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 120000, 50000, clientId));

        // Insert transaction events
        ksession.insert(new TransactionEvent(clientId, 25000, TransactionEvent.Type.OUTCOME, clientId));
        ksession.insert(new TransactionEvent(clientId, 5000, TransactionEvent.Type.OUTCOME, clientId));
        ksession.insert(new TransactionEvent(clientId, 60000, TransactionEvent.Type.OUTCOME, clientId));
        ksession.insert(new TransactionEvent(clientId, 50000, TransactionEvent.Type.OUTCOME, clientId));
        ksession.insert(new TransactionEvent(clientId, 120000, TransactionEvent.Type.OUTCOME, clientId));

        // Fire all rules
        long ruleFireCount = ksession.fireAllRules();
        System.out.println("Rules fired: " + ruleFireCount);

        // Insert additional transaction event to trigger more rules
        ksession.insert(new TransactionEvent(clientId, 202000, TransactionEvent.Type.OUTCOME, clientId));
        ruleFireCount = ksession.fireAllRules();
        System.out.println("Rules fired: " + ruleFireCount);
        
        for (int i = 0; i < 4; i++) { // Simulate 4 weeks
            clock.advanceTime(7, TimeUnit.DAYS); // Move clock forward by one week
            ksession.fireAllRules();
        }
    }
}
