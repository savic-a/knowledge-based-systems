package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Alarm;
import com.ftn.sbnz.model.Alarm.Level;

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
        kSession.insert( new Alarm(2L, "Drugi alarm", Level.MEDIUM, 1L));
        kSession.insert( new Alarm(3L, "Treci alarm", Level.HIGH, 1L));

        kSession.fireAllRules();
    }
}
