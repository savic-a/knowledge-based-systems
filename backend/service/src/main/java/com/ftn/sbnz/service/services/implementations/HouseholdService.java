package com.ftn.sbnz.service.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.HouseholdDTO;
import com.ftn.sbnz.dto.TransactionDTO;
import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.service.repositories.ClientRepository;
import com.ftn.sbnz.service.repositories.HouseholdRepository;
import com.ftn.sbnz.service.services.interfaces.IService;

@Service
public class HouseholdService implements IService<Household>{

    private final KieContainer kieContainer;

    @Autowired
    public HouseholdRepository repository;

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public HouseholdService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Household> getAll() {
        return this.repository.findAll();
    }
    
    public List<HouseholdDTO> getHouseholdByClientId(Long clientId) {
        List<Household> households = this.repository.findByFirstPerson(clientId);
        return households.stream()
            .map(household -> new HouseholdDTO(clientRepository.findById(household.getSecondPerson()).orElse(null).getEmail()))
            .collect(Collectors.toList());
    }

    public Household addHousehold(Long clientId, TransactionDTO dto) {
        // CreditCard card = this.creditCardRepository.findByClientId(clientId);
        // double newBalance = card.getBalance();

        // FinancialGoal goal = this.goalRepository.findByClientId(clientId);

        // Transaction transaction = new Transaction();
        // transaction.setValue(dto.getValue());
        // transaction.setDate(Timestamp.valueOf(LocalDateTime.now()));
        // if(dto.getType() == 1) {
        //     transaction.setType(Transaction.Type.OUTCOME);
        //     newBalance -= dto.getValue(); 
        // } else {
        //     transaction.setType(Transaction.Type.INCOME);
        //     newBalance += dto.getValue();
        // }
        // transaction.setClientId(clientId);
        // transaction.setCategory(dto.convertToCategory());
        // transaction.setIsProcessed(false);

        // // edit value on credit card
        // card.setBalance(newBalance);
        // creditCardRepository.save(card);

        // // edit financial goal
        // if(goal != null) {
        //     goal.setCurrentBalance(newBalance);
        //     goalRepository.save(goal);
        // }
        // // insert transaction to kiesession
        // System.out.println("---------------------------------");
        // KieSession kieSession = KieSessionService.getKieSession();
        // kieSession.insert(transaction);
        // kieSession.fireAllRules();
        // System.out.println("---------------------------------");
        // transaction.setIsProcessed(true);

        // // save new transaction
        // return repository.save(transaction);
        return null;
    }
}
