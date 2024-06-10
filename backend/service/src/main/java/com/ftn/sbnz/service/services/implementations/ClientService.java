package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.enumeration.Category;
import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.model.ClientFivePurchases;
import com.ftn.sbnz.model.ClientThreePurchases;
import com.ftn.sbnz.service.repositories.ClientFivePurchasesRepository;
import com.ftn.sbnz.service.repositories.ClientRepository;
import com.ftn.sbnz.service.repositories.ClientThreePurchasesRepository;
import com.ftn.sbnz.service.services.interfaces.IClientService;


@Service
public class ClientService implements IClientService, UserDetailsService {
    private final KieContainer kieContainer;

    @Autowired
    public ClientRepository repository;
    @Autowired
    public ClientThreePurchasesRepository clientThreePurchasesRepository;
    @Autowired
    public ClientFivePurchasesRepository clientFivePurchasesRepository;

    @Autowired
    public ClientService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public ClientService() {
        this.kieContainer = null;
    }

    public List<Client> getAll() {
        List<Client> clients = this.repository.findAll();
    
        for (Client client: clients) { 
            List<ClientThreePurchases> threePurchases = this.clientThreePurchasesRepository.findByClientId(client.getId());
            for (ClientThreePurchases purchase: threePurchases) {
                List<Category> categories = client.getThreePurchases();
                categories.add(purchase.getCategory());
                client.setThreePurchases(categories);
            }
            List<ClientFivePurchases> fivePurchases = this.clientFivePurchasesRepository.findByClientId(client.getId());
            for (ClientFivePurchases purchase: fivePurchases) {
                List<Category> categories = client.getFivePurchases();
                categories.add(purchase.getCategory());
                client.setFivePurchases(categories);
            }


        }
        
        for (Client client: clients) {
            System.out.println(client);
        }

        return clients;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email '%s' is not found!", email)));
    }
    
	public Client findByEmail(String email) throws UsernameNotFoundException {
		return this.repository.findByEmail(email).orElseThrow(()
				-> new UsernameNotFoundException(String.format("User with email '%s' is not found!", email)));
	}
}
