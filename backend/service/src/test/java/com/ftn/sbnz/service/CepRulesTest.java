package com.ftn.sbnz.service;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.kie.api.KieServices;
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

       // Insert initial test data
       ksession.insert(new Client(1L, "PERA", "PERIC", "pera@gmail.com", "123", new ArrayList<>(), new ArrayList<>(), false, false));
       ksession.insert(new Budget(1L, 100000, 1L));
       ksession.insert(new FinancialGoal(1L, "Savings Goal", "Save money for a vacation", Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 120000, 50000, 1L));

       ksession.insert(new Client(2L, "PERA", "PERIC", "pera@gmail.com", "123", new ArrayList<>(), new ArrayList<>(), false, false));
       ksession.insert(new Budget(2L, 100000, 2L));
       ksession.insert(new FinancialGoal(2L, "Savings Goal", "Save money for a vacation", Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 120000, 50000, 2L));

      ksession.insert(new Client(3L, "PERA", "PERIC", "pera@gmail.com", "123", new ArrayList<>(), new ArrayList<>(), false, false));
      ksession.insert(new Budget(3L, 100000, 3L));
      ksession.insert(new FinancialGoal(3L, "Savings Goal", "Save money for a vacation", Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 120000, 10000, 3L));
       
      // Simulate first week - budget overrun
       ksession.insert(new TransactionEvent(1L, 120000, TransactionEvent.Type.OUTCOME, 1L)); // Exceeds budget
       ksession.fireAllRules();
       clock.advanceTime(7, TimeUnit.DAYS); // Move clock forward by one week

       // Simulate second week - sudden jump in costs
       ksession.insert(new TransactionEvent(2L, 5000, TransactionEvent.Type.OUTCOME, 2L));
       ksession.insert(new TransactionEvent(3L, 50000, TransactionEvent.Type.OUTCOME, 2L)); // Sudden jump in cost
       ksession.fireAllRules();
       clock.advanceTime(7, TimeUnit.DAYS); // Move clock forward by one week

       // Simulate third week - financial goal achieved
       ksession.insert(new TransactionEvent(4L, 20000, TransactionEvent.Type.OUTCOME, 3L)); // Achieve financial goal
       ksession.fireAllRules();
       clock.advanceTime(7, TimeUnit.DAYS); // Move clock forward by one week

       // Final rule firing to ensure all rules have fired
       ksession.fireAllRules();
    }
}