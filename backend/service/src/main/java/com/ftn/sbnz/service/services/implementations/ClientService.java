package com.ftn.sbnz.service.services.implementations;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.service.repositories.ClientRepository;
import com.ftn.sbnz.service.services.interfaces.IService;


@Service
public class ClientService implements IService<Client> {
    private final KieContainer kieContainer;

    @Autowired
    public ClientRepository repository;

    @Autowired
    public ClientService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Client> getAll() {
        return this.repository.findAll();
    }
    
}
