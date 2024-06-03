package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.Client;
import com.ftn.sbnz.service.services.implementations.ClientService;
import com.ftn.sbnz.service.services.interfaces.IClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private IClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> getAllCreditCards() {
        List<Client> clients = service.getAll();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }
    
}
