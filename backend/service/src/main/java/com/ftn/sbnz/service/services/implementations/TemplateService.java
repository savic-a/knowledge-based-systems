package com.ftn.sbnz.service.services.implementations;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Report;

@Service
public class TemplateService {

    public static void createReport(Report report) {
        InputStream template = TemplateService.class.getResourceAsStream("/rules/template/template2.drt");
        System.out.println("--------------------------------");
        System.out.println(template);

        List<Report> data = new ArrayList<>();
        data.add(report);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println("Generated DRL:\n" + drl);

        KieSession kSession = createKieSessionFromDRL(drl);
        doTest2(kSession);
    }

    private static KieSession createKieSessionFromDRL(String drl){
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

    private static void doTest2(KieSession ksession){
        ksession.insert("generate report");
        ksession.fireAllRules();
    }
    
}
