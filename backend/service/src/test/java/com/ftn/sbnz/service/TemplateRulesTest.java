package com.ftn.sbnz.service;

import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

public class TemplateRulesTest {
    
    @Test
    public void testTemplate() {
        InputStream template = TemplateRulesTest.class.getResourceAsStream("/rules/template/template.drt");
        System.out.println(template);

        DataProvider dataProvider = new ArrayDataProvider(new String[][] {
            new String[]{"20 000"},
            new String [] {"some string"},
        });

        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        System.out.println("--------------------------------");
        System.out.println(drl);

        KieSession kSession = createKieSessionFromDRL(drl);
        doTest(kSession);
    }

    private void doTest(KieSession ksession){
        ksession.insert("calculate budget");
        ksession.fireAllRules();
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}
