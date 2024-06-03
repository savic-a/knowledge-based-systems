package com.ftn.sbnz.service.services.interfaces;

import java.util.List;

import com.ftn.sbnz.model.Transaction;


public interface ITransactionService {
    
    public List<Transaction> getAll();
}
