package com.ftn.sbnz.service.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.TransactionDTO;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.service.repositories.CreditCardRepository;
import com.ftn.sbnz.service.repositories.FinancialGoalRepository;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.service.repositories.TransactionRepository;
import com.ftn.sbnz.service.services.interfaces.IService;
import com.ftn.sbnz.singleton.KieSessionService;

@Service
public class TransactionService implements IService<Transaction> {
    private final KieContainer kieContainer;

    @Autowired
    public TransactionRepository repository;

    @Autowired
    public CreditCardRepository creditCardRepository;

    @Autowired
    public FinancialGoalRepository goalRepository;

    @Autowired
    public TransactionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Transaction> getAll() {
        return this.repository.findAll();
    }
    
    public List<Transaction> getTransactionsByClientId(Long clientId) {
        return this.repository.findByClientId(clientId);
    }

    public Transaction addTransaction(Long clientId, TransactionDTO dto) {
        CreditCard card = this.creditCardRepository.findByClientId(clientId);
        double newBalance = card.getBalance();

        FinancialGoal goal = this.goalRepository.findByClientId(clientId);

        Transaction transaction = new Transaction();
        transaction.setValue(dto.getValue());
        transaction.setDate(Timestamp.valueOf(LocalDateTime.now()));
        if(dto.getType() == 1) {
            transaction.setType(Transaction.Type.OUTCOME);
            newBalance -= dto.getValue(); 
        } else {
            transaction.setType(Transaction.Type.INCOME);
            newBalance += dto.getValue();
        }
        transaction.setClientId(clientId);
        transaction.setCategory(dto.convertToCategory());
        transaction.setIsProcessed(false);

        // edit value on credit card
        card.setBalance(newBalance);
        creditCardRepository.save(card);

        // edit financial goal
        if(goal != null) {
            goal.setCurrentBalance(newBalance);
            goalRepository.save(goal);
        }
        // insert transaction to kiesession
        System.out.println("---------------------------------");
        KieSession kieSession = KieSessionService.getKieSession();
        kieSession.insert(transaction);
        kieSession.fireAllRules();
        System.out.println("---------------------------------");
        transaction.setIsProcessed(true);

        // save new transaction
        return repository.save(transaction);
    }
}
