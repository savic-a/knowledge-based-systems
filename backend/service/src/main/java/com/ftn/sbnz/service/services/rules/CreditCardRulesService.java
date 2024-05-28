package com.ftn.sbnz.service.services.rules;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.model.Transaction.Type;
import com.ftn.sbnz.service.repositories.CreditCardRepository;

@Service
public class CreditCardRulesService {
    private final KieContainer kieContainer;

    @Autowired
    public CreditCardRepository repository;

    @Autowired
    public CreditCardRulesService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules() {
        System.out.println("LAAAAAAAAAAAAAAAAAA");
        KieSession kSession = kieContainer.newKieSession("ksession-credit-card");

        kSession.insert( new CreditCard(1L, 2000.00, 1L));
        kSession.insert( new Transaction(1L, 200.00, Timestamp.valueOf(LocalDateTime.now()), Type.INCOME, Category.PRIVATE, 1L));
        kSession.insert( new Transaction(2L, 400.00, Timestamp.valueOf(LocalDateTime.now()), Type.OUTCOME, Category.FOOD, 1L));

        kSession.fireAllRules();
    }

    public List<CreditCard> getAll() {
        return repository.findAll();
    }
}
