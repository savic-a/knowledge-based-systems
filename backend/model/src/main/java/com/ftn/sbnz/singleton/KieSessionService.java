package com.ftn.sbnz.singleton;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.rule.FactHandle;

public class KieSessionService {
    private static KieSession kieSession = null;

    private KieSessionService() {

    }

    public static KieSession getKieSession() {
        if (kieSession == null) {
            System.out.println("creating kie sessionnnnnn");
            KieServices ks = KieServices.Factory.get();
            KieContainer kc = ks.newKieClasspathContainer();

            KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
            kieSession = kc.newKieSession("k-session-all", config);
        }
        System.out.println("returning kie sesionnnnnnnn");
        return kieSession;
    }

    public static void printKieSessionObjects() {
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            Object object = kieSession.getObject(factHandle);
            System.out.println(object);
        }
    }
}
