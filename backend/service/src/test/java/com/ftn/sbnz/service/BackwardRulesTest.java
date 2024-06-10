package com.ftn.sbnz.service;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;

import com.ftn.sbnz.event.AnalysisTransaction;
import com.ftn.sbnz.model.Household;

public class BackwardRulesTest {
    
    @Test
    public void testBackward() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        KieSession ksession = kc.newKieSession("ksession-backward", config);
    
        Household household = new Household(1L, 2L);
        Household household2 = new Household(2L, 3L);
        ksession.insert(household);
        ksession.insert(household2);
        ksession.insert(new AnalysisTransaction(3L, AnalysisTransaction.FinancialGoalType.SUDDEN_JUMP));
        ksession.fireAllRules();

    }

    @Test
    public void testBackward2() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        KieSession ksession = kc.newKieSession("ksession-backward", config);
    
        Household household = new Household(1L, 2L);
        Household household2 = new Household(2L, 3L);
        ksession.insert(household);
        ksession.insert(household2);
        ksession.insert(new AnalysisTransaction(4L, AnalysisTransaction.FinancialGoalType.OVERRUN_BUDGET));
        ksession.fireAllRules();
    }

    @Test
    public void testBackward3() {
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();

        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
        KieSession ksession = kc.newKieSession("ksession-backward", config);
    
        ksession.insert(new Household(1L, 2L));
        ksession.insert(new Household(2L, 3L));
        ksession.insert(new Household(3L, 4L));
        ksession.insert(new Household(3L, 5L));
        ksession.insert(new AnalysisTransaction(5L, AnalysisTransaction.FinancialGoalType.OVERRUN_BUDGET));
        ksession.fireAllRules();

    }
}
