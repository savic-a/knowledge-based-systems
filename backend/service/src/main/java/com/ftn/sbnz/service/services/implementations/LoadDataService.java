package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import javax.annotation.PostConstruct;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Budget;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.CreditCard;
import com.ftn.sbnz.model.FinancialGoal;
import com.ftn.sbnz.model.Household;
import com.ftn.sbnz.model.Report;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.service.services.interfaces.ILoadDataService;
import com.ftn.sbnz.service.services.interfaces.IService;
import com.ftn.sbnz.singleton.KieSessionService;

@Service
public class LoadDataService implements ILoadDataService {
    private final KieContainer kieContainer;
    private IService<Client> clientService;
    private IService<CreditCard> creditCardService;
    private IService<Transaction> transactionService;
    private IService<Budget> budgetService;
    private IService<FinancialGoal> financialGoalService;
    private IService<Report> reportService;
    private IService<Household> householdService;

    @Autowired
    public LoadDataService(KieContainer kieContainer,
                           ClientService clientService, 
                           CreditCardService creditCardService, 
                           TransactionService transactionService, 
                           BudgetService budgetService, 
                           FinancialGoalService financialGoalService, 
                           ReportService reportService,
                           HouseholdService householdService) {
        this.kieContainer = kieContainer;
        this.clientService = clientService;
        this.creditCardService = creditCardService;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
        this.financialGoalService = financialGoalService;
        this.reportService = reportService;
        this.householdService = householdService;
    }

    @PostConstruct
    public void init() {
        loadData(this.clientService);
        loadData(this.creditCardService);
        loadData(this.transactionService);
        loadData(this.budgetService);
        loadData(this.financialGoalService);
        loadData(this.reportService);
        loadData(this.householdService);
    }
    
    @Override
    public <T extends IService<E>, E> void loadData(T service) {
        List<E> items = service.getAll();
        
        KieSession kSession = KieSessionService.getKieSession();
        for (E item : items) {
            kSession.insert(item);
        }
        kSession.fireAllRules();
    }
}
