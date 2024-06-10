package com.ftn.sbnz.service;

import java.sql.Timestamp;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.BudgetExceeding;
import com.ftn.sbnz.model.Transaction;

public class ForwardRulesTest {

    @Test
    public void forwardRulesTest() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        KieSession ksession = kc.newKieSession("ksession-forward-1", config);
        
        ksession.insert(new Budget(1L, 50000, 1L));
        ksession.insert(new Transaction(6L, 40000, Timestamp.valueOf("2024-01-01 00:00:00"), Transaction.Type.OUTCOME, 1L, Category.HEALTH_AND_CARE));
        ksession.insert(new Transaction(7L, 30000, Timestamp.valueOf("2024-01-01 00:00:00"), Transaction.Type.OUTCOME, 1L, Category.FOOD));
        ksession.insert(new Transaction(8L, 15000, Timestamp.valueOf("2024-01-01 00:00:00"), Transaction.Type.OUTCOME, 1L, Category.FOOD));
        ksession.insert(new Transaction(9L, 12000, Timestamp.valueOf("2024-01-01 00:00:00"), Transaction.Type.OUTCOME, 1L, Category.PRIVATE));
        ksession.insert(new BudgetExceeding(1L));
        ksession.fireAllRules();
    }
    
}
