package com.ftn.sbnz.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import com.ftn.sbnz.dto.TemplateDTO;
import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.FinancialGoal;

public class TemplateRulesTest {
    
    @Test
    public void testTemplate() {
        InputStream template = TemplateRulesTest.class.getResourceAsStream("/rules/template/template.drt");
        System.out.println(template);

        List<TemplateDTO> data = new ArrayList<>();
        data.add(new TemplateDTO(20000, 1L));
        data.add(new TemplateDTO(18000, 2L));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        KieSession kSession = createKieSessionFromDRL(drl);
        doTest(kSession);
    }

    private void doTest(KieSession ksession){
        ksession.insert(new Budget(1L, 20000, 1L));
        ksession.insert(new FinancialGoal(1L, "Savings Goal", "Save money for a vacation", 
        Timestamp.valueOf("2024-01-01 00:00:00"), 100000, Timestamp.valueOf("2024-12-31 23:59:59"), 
        12000, 10000, 1L));
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
