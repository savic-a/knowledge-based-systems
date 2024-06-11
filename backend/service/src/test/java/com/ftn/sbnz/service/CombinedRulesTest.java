package com.ftn.sbnz.service;

import java.sql.Timestamp;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.event.Alarm;
import com.ftn.sbnz.event.AnalysisTransaction;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.model.Transaction;

public class CombinedRulesTest {

    KieSession kieSession = null;

    public void getKieSession() {
        if (this.kieSession == null) {
            KieServices ks = KieServices.Factory.get();
            KieContainer kc = ks.newKieClasspathContainer();

            KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
            this.kieSession = kc.newKieSession("k-session-all", config);
        }
    }
    
    @Test
    public void testReports() {
        getKieSession();
        this.kieSession.insert(new Alarm(1L, "desccc", Alarm.Level.LOW, 1L));
        this.kieSession.fireAllRules();
    }

    @Test
    public void testBackwardAndCep() {
        getKieSession();
        Household household = new Household(1L, 2L);
        Household household2 = new Household(2L, 3L);
        this.kieSession.insert(household);
        this.kieSession.insert(household2);
        this.kieSession.insert(new AnalysisTransaction(3L, AnalysisTransaction.FinancialGoalType.SUDDEN_JUMP));
        this.kieSession.fireAllRules();
    }

    @Test
    public void testCreditCard() {
        getKieSession();
        this.kieSession.insert(new CreditCard(1L, 2000, 1L));
        this.kieSession.insert(new Transaction(1L, 200, Timestamp.valueOf("2024-06-09 00:00:00"), Transaction.Type.OUTCOME, 1L, Category.ENTERTAINMENT));
        this.kieSession.fireAllRules();
    }

}
