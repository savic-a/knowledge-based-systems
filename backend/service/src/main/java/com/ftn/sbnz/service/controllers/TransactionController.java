package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.dto.TransactionDTO;
import com.ftn.sbnz.model.Transaction;
import com.ftn.sbnz.service.services.implementations.TransactionService;
import com.ftn.sbnz.service.services.interfaces.IService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private IService<Transaction> service;

    @Autowired
    private TransactionService tService;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = service.getAll();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Transaction>> getTransactionsByClientId(@PathVariable Long clientId) {
        List<Transaction> transactions = tService.getTransactionsByClientId(clientId);
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable Long clientId,
                                                          @RequestBody TransactionDTO request) {
        Transaction newTransaction = tService.addTransaction(clientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }
}
