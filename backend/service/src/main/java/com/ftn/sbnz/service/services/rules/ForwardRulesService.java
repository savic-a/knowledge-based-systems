package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.Budget;
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
        kSession.insert( new Client(1L, "PERA", "PERIC", "pera@gmail.com",  "123", new ArrayList<>(), new ArrayList<>(), false, false));

        kSession.insert(new Budget(1L, 50000, 1L));

       // Insert Income Transactions
        kSession.insert(new Transaction(1L, 50000, Timestamp.valueOf("2024-06-01 10:00:00"), Transaction.Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert(new Transaction(2L, 30000, Timestamp.valueOf("2024-06-01 12:00:00"), Transaction.Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert(new Transaction(3L, 70000, Timestamp.valueOf("2024-06-01 14:00:00"), Transaction.Type.INCOME, Category.PRIVATE, 1L));

        // Insert Outcome Transactions (Shopping) - More than 5 transactions
        kSession.insert(new Transaction(4L, 15000, Timestamp.valueOf("2024-06-01 10:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        kSession.insert(new Transaction(5L, 5000, Timestamp.valueOf("2024-06-01 12:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        kSession.insert(new Transaction(6L, 8000, Timestamp.valueOf("2024-06-01 14:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        kSession.insert(new Transaction(7L, 9000, Timestamp.valueOf("2024-06-01 16:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        kSession.insert(new Transaction(8L, 9000, Timestamp.valueOf("2024-06-01 16:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        kSession.insert(new Transaction(9L, 11000, Timestamp.valueOf("2024-06-02 10:00:00"), Transaction.Type.OUTCOME, Category.SHOPPING, 1L));
        
        // Insert Outcome Transactions (Entertainment) - Exactly 3 transactions
        kSession.insert(new Transaction(10L, 10000, Timestamp.valueOf("2024-06-01 10:00:00"), Transaction.Type.OUTCOME, Category.ENTERTAINMENT, 1L));
        kSession.insert(new Transaction(11L, 10000, Timestamp.valueOf("2024-06-01 12:00:00"), Transaction.Type.OUTCOME, Category.ENTERTAINMENT, 1L));
        kSession.insert(new Transaction(12L, 10000, Timestamp.valueOf("2024-06-01 14:00:00"), Transaction.Type.OUTCOME, Category.ENTERTAINMENT, 1L));

        // Insert Outcome Transactions (Food) - to trigger another category for flag setting
        kSession.insert(new Transaction(13L, 500, Timestamp.valueOf("2024-06-01 10:00:00"), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(14L, 7000, Timestamp.valueOf("2024-06-01 12:00:00"), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(15L, 6000, Timestamp.valueOf("2024-06-01 14:00:00"), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(16L, 6000, Timestamp.valueOf("2024-06-01 16:00:00"), Transaction.Type.OUTCOME, Category.FOOD, 1L));
        kSession.insert(new Transaction(17L, 6000, Timestamp.valueOf("2024-06-02 18:00:00"), Transaction.Type.OUTCOME, Category.FOOD, 1L));

        kSession.fireAllRules();
    }
    
    // public static Timestamp getStartOfMonth(Timestamp timestamp) {
    //     Calendar cal = Calendar.getInstance();
    //     cal.setTimeInMillis(timestamp.getTime());
    //     cal.set(Calendar.DAY_OF_MONTH, 1);
    //     cal.set(Calendar.HOUR_OF_DAY, 0);
    //     cal.set(Calendar.MINUTE, 0);
    //     cal.set(Calendar.SECOND, 0);
    //     cal.set(Calendar.MILLISECOND, 0);
    //     return new Timestamp(cal.getTimeInMillis());
    // }
}
