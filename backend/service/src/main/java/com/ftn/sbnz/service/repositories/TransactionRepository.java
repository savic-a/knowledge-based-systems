package com.ftn.sbnz.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
