package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.util.Calendar;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.Transaction;


@Service
public class ForwardRulesService {
    private final KieContainer kieContainer;

    @Autowired
    public ForwardRulesService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules() {
        KieSession kSession = kieContainer.newKieSession("ksession-forward-1");
        kSession.insert( new Client(1L, "PERA", "PERIC", "pera@gmail.com",  "123"));

        // Transakcije za klijenta u ovom mesecu
        Timestamp today = new Timestamp(System.currentTimeMillis());
        Timestamp startOfMonth = getStartOfMonth(today);

        kSession.insert(new Transaction(1L, 50000.0, startOfMonth, Transaction.Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert(new Transaction(2L, 60000.0, startOfMonth, Transaction.Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert(new Transaction(3L, 15000.0, startOfMonth, Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        // kSession.insert(new Transaction(4L, 25000.0, startOfMonth, Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        // kSession.insert(new Transaction(5L, 30000.0, startOfMonth, Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        // kSession.insert(new Transaction(6L, 20000.0, startOfMonth, Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        // kSession.insert(new Transaction(7L, 45000.0, startOfMonth, Transaction.Type.OUTCOME, Category.SHOPPING, 1L));


        kSession.fireAllRules();
    }
    
    // Funkcija koja vraća početak trenutnog meseca za datu vremensku oznaku
    public static Timestamp getStartOfMonth(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }
}
