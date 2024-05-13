package com.ftn.sbnz.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.Alarm;
import com.ftn.sbnz.util.KnowledgeSessionHelper;

public class Test {
    public static void main() {
        try {
            System.out.println("usao u test main...");
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "ksession-reports");

            Alarm alarm = new Alarm(1L, "Desio se strasan alarm", Alarm.Level.HIGH, 1L);
            kSession.insert(alarm);
            kSession.fireAllRules();
        }
        catch(Throwable t){
            t.printStackTrace();
        }
    }

        
}
