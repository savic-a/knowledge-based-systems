package com.ftn.sbnz.service.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.FinancialGoalDTO;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.service.repositories.CreditCardRepository;
import com.ftn.sbnz.service.repositories.FinancialGoalRepository;
import com.ftn.sbnz.service.services.interfaces.IService;


@Service
public class FinancialGoalService implements IService<FinancialGoal> {
    private final KieContainer kieContainer;

    @Autowired
    public FinancialGoalRepository repository;

    @Autowired
    public CreditCardRepository creditCardRepository;

    @Autowired
    public FinancialGoalService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<FinancialGoal> getAll() {
        return this.repository.findAll();
    }

    public FinancialGoal getFinancialGoalByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    public FinancialGoal addFinancialGoal(Long clientId, FinancialGoalDTO goalDTO) {
        CreditCard card = this.creditCardRepository.findByClientId(clientId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime targetDate = LocalDateTime.parse(goalDTO.getTargetDate() + "T00:00:00", formatter);
        FinancialGoal financialGoal = new FinancialGoal();
        financialGoal.setName(goalDTO.getName());
        financialGoal.setDescription(goalDTO.getDescription());
        financialGoal.setGenerationDate(Timestamp.valueOf(LocalDateTime.now()));
        financialGoal.setTargetValue(goalDTO.getTargetValue());
        financialGoal.setTargetDate(Timestamp.valueOf(targetDate));
        financialGoal.setCurrentBalance(card.getBalance());
        financialGoal.setStartBalance(card.getBalance());
        financialGoal.setClientId(clientId);

        return repository.save(financialGoal);
    }
}
