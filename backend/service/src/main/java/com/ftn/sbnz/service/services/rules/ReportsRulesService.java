package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.event.Alarm;
import com.ftn.sbnz.event.Alarm.Level;
import com.ftn.sbnz.model.FirstOfMonthEvent;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.model.Transaction.Category;
import com.ftn.sbnz.model.Transaction.Type;

@Service
public class ReportsRulesService {
    private final KieContainer kieContainer;

    @Autowired
    public ReportsRulesService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules() {
        KieSession kSession = kieContainer.newKieSession("ksession-reports");
        kSession.insert( new Alarm(1L, "Prvi alarm", Level.LOW, 1L));
        kSession.insert( new Alarm(2L, "Drugi alarm", Level.LOW, 1L));
        kSession.insert( new Alarm(3L, "Treci alarm", Level.LOW, 1L));

        kSession.insert( new FirstOfMonthEvent());
        kSession.insert( new Transaction(1L, 200.00, Timestamp.valueOf(LocalDateTime.of(2024, 4, 15, 12, 0, 0)), Type.INCOME, 1L, Category.GROCERIES));
        kSession.insert( new Transaction(2L, 800.00, Timestamp.valueOf(LocalDateTime.of(2024, 4, 25, 10, 0, 0)), Type.INCOME, 1L, Category.RENT));
        kSession.insert( new Transaction(3L, 400.00, Timestamp.valueOf(LocalDateTime.of(2024, 5, 10, 12, 0, 0)), Type.INCOME, 1L, Category.UTILITES));
        // kSession.insert( new FirstOfMonthEvent());

        kSession.fireAllRules();
    }
}
